package com.android.weatherapp.renderforest.domain.usecase

import com.android.weatherapp.renderforest.domain.model.ApiError

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: ApiError?)
}

