package com.android.weatherapp.renderforest.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.weatherapp.renderforest.domain.model.ApiError
import com.android.weatherapp.renderforest.domain.model.MainWeatherResponse
import com.android.weatherapp.renderforest.domain.model.Params
import com.android.weatherapp.renderforest.domain.repository.DataBaseRepository
import com.android.weatherapp.renderforest.domain.usecase.GetWeatherUseCase
import com.android.weatherapp.renderforest.domain.usecase.UseCaseResponse
import com.android.weatherapp.renderforest.utils.NetworkUtil
import com.android.weatherapp.renderforest.utils.convertModelToEntity
import kotlinx.coroutines.cancel

class HomeViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val dataBaseRepository: DataBaseRepository
) : ViewModel() {

    val weatherData = MutableLiveData<MainWeatherResponse>()
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
                        val weatherEntity = convertModelToEntity(result.daily)
//                    dataBaseRepository.add(weatherEntity)
//                    dataBaseRepository.getSavedNewsData
                        weatherData.postValue(result)
                        showProgressbar.value = false
                    }

                    override fun onError(apiError: ApiError?) {
                        messageData.value = apiError?.getErrorMessage()
                        showProgressbar.value = false
                    }


                }
            )
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}