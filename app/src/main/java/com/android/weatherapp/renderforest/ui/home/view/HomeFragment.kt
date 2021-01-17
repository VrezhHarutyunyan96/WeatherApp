package com.android.weatherapp.renderforest.ui.home.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.weatherapp.renderforest.base.BaseFragment
import com.android.weatherapp.renderforest.clean.BuildConfig.API_KEY
import com.android.weatherapp.renderforest.clean.R
import com.android.weatherapp.renderforest.clean.databinding.FragmentHomeBinding
import com.android.weatherapp.renderforest.domain.model.Params
import com.android.weatherapp.renderforest.ui.home.viewmodel.HomeViewModel
import com.android.weatherapp.renderforest.utils.Constants
import com.android.weatherapp.renderforest.utils.PermissionUtils
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    val LOCATION_PERMISSION_REQUEST_CODE = 1001

    val tagName = "HomeFragment"
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private val adapter: HomeAdapter = HomeAdapter()

    private val viewModel: HomeViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when {
            PermissionUtils.isAccessFineLocationGranted(requireContext()) -> {
                when {
                    PermissionUtils.isLocationEnabled(requireContext()) -> {
                        setUpLocationListener()
                    }
                    else -> {
                        PermissionUtils.showGPSNotEnabledDialog(requireContext())
                    }
                }
            }
            else -> {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ), LOCATION_PERMISSION_REQUEST_CODE
                )
            }
        }
    }


    override fun initialize(savedInstanceState: Bundle?) {
        super.initialize(savedInstanceState)
        initAdapter()
        initObservers()
    }

    @SuppressLint("MissingPermission")
    private fun setUpLocationListener() {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())


        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    Log.d(tagName, "loop ${location?.latitude} ${location?.longitude}")
                }
            }
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                viewModel.getWeather(
                    Params(
                        API_KEY,
                        location?.latitude.toString(),
                        location?.longitude.toString(),
                        Constants.DATE_REQUEST_PARAMS,
                        Constants.UNITS_REQUEST_PARAMS
                    )
                )

                Log.d(tagName, " last ${location?.latitude} ${location?.longitude}")
            }
        createLocationRequest()
    }

    @SuppressLint("MissingPermission")
    private fun createLocationRequest() {
        val locationRequest = LocationRequest.create()?.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest!!)
        val client = LocationServices.getSettingsClient(requireContext())
        val task = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }
        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                try {
                    exception.startResolutionForResult(
                        requireContext() as Activity?,
                        1
                    )
                } catch (sendEx: IntentSender.SendIntentException) {
                }
            }
        }
    }

    private fun initAdapter() {

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    fun initObservers() {

        with(viewModel) {

            weatherData.observe(this@HomeFragment, Observer {
                adapter.mWeatherList = it.daily
                progressBar.visibility = GONE
            })

            showProgressbar.observe(this@HomeFragment, Observer { isVisible ->
                progressBar.visibility = if (isVisible) VISIBLE else GONE
            })
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d("requestCode", requestCode.toString())
        when (requestCode) {

            LOCATION_PERMISSION_REQUEST_CODE -> {

                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    when {
                        PermissionUtils.isLocationEnabled(requireContext()) -> {
                            setUpLocationListener()
                        }
                        else -> {
                            PermissionUtils.showGPSNotEnabledDialog(requireContext())
                        }
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.location_permission_not_granted),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}