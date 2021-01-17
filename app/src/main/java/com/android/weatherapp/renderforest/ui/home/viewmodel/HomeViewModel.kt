package com.android.weatherapp.renderforest.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.weatherapp.renderforest.data.local.entitiy.WeatherEntity
import com.android.weatherapp.renderforest.domain.model.ApiError
import com.android.weatherapp.renderforest.domain.model.MainWeatherResponse
import com.android.weatherapp.renderforest.domain.model.Params
import com.android.weatherapp.renderforest.domain.repository.DataBaseRepository
import com.android.weatherapp.renderforest.domain.usecase.GetWeatherUseCase
import com.android.weatherapp.renderforest.domain.usecase.UseCaseResponse
import com.android.weatherapp.renderforest.utils.NetworkUtil
import com.android.weatherapp.renderforest.utils.PojoToEntity
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val dataBaseRepository: DataBaseRepository
) : ViewModel() {

    val weatherData = MutableLiveData<MainWeatherResponse>()
    val weatherDbData = MutableLiveData<WeatherEntity>()
    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun getWeather(params: Params) {

        if (NetworkUtil.isNetworkAvailable()) {

            showProgressbar.value = true
            getWeatherUseCase.invoke(
                viewModelScope,
                params,
                object : UseCaseResponse<MainWeatherResponse> {

                    override fun onSuccess(result: MainWeatherResponse) {
                        val data = PojoToEntity().map(result)
                        viewModelScope.launch {
                            dataBaseRepository.add(data)
                        }

                        dataBaseRepository.getSavedNewsData
                        weatherData.postValue(result)
                        getDbData()
                        showProgressbar.value = false
                    }

                    override fun onError(apiError: ApiError?) {
                        messageData.value = apiError?.getErrorMessage()
                        showProgressbar.value = false
                    }


                }
            )
        } else {
            getDbData()
        }
    }

    fun getDbData() = viewModelScope.launch {
        dataBaseRepository.getSavedNewsData.collect {
            weatherDbData.postValue(it)
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}