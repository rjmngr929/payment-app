package com.example.paymentapplication.di.exception

import com.example.paymentapplication.di.exception.NetworkExceptionHandler
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkInterceptor(private val exceptionHandler: NetworkExceptionHandler) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            chain.proceed(chain.request())
        } catch (e: Exception) {
            val errorMessage = exceptionHandler.handleException(e)
            throw IOException(errorMessage, e)
        }
    }
}