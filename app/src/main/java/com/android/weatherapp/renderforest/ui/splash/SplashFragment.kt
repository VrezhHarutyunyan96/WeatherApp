package com.android.weatherapp.renderforest.ui.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.android.weatherapp.renderforest.base.BaseFragment
import com.android.weatherapp.renderforest.clean.R
import com.android.weatherapp.renderforest.clean.databinding.FragmentSplashBinding
import kotlinx.coroutines.*
import org.koin.androidx.scope.fragmentScope

class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch(Dispatchers.Main) {
            delay(3000)
            navigate(R.id.action_splashFragment_to_homeFragment)

        }
    }
}