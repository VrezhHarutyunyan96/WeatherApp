package com.android.weatherapp.renderforest.base

import com.android.weatherapp.renderforest.domain.exception.traceErrorException
import com.android.weatherapp.renderforest.domain.usecase.UseCaseResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

abstract class BaseUseCase<Type, in Params>() where Type : Any {

    abstract suspend fun run(params: Params): Type


    fun invoke(scope: CoroutineScope, params: Params, onResult: UseCaseResponse<Type>?) {

        scope.launch {
//            try {
                val result = run(params)
                onResult?.onSuccess(result)
//            }
//            catch (e: CancellationException) {
//                e.printStackTrace()
//                onResult?.onError(traceErrorException(e))
//            } catch (e: Exception) {
//                e.printStackTrace()
//                onResult?.onError(traceErrorException(e))
//            }
        }
    }

}