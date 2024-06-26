package com.example.paymentapplication.di

import com.example.paymentapplication.api.AuthInterceptor
import com.example.paymentapplication.api.UserAPI
import com.example.paymentapplication.di.exception.NetworkExceptionHandler
import com.example.paymentapplication.di.exception.NetworkInterceptor
import com.example.paymentapplication.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {


    @Provides
    fun provideNetworkExceptionHandler(): NetworkExceptionHandler {
        return NetworkExceptionHandler()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    fun provideNetworkInterceptor(exceptionHandler: NetworkExceptionHandler): NetworkInterceptor {
        return NetworkInterceptor(exceptionHandler)
    }


    @Singleton
    @Provides
    fun providesRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: AuthInterceptor, networkInterceptor: NetworkInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(networkInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true).build()
    }

    @Singleton
    @Provides
    fun providesUserAPI(retrofitBuilder: Retrofit.Builder): UserAPI {
        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
        return retrofitBuilder.client(okHttpClient).build().create(UserAPI::class.java)
    }

//    @Singleton
//    @Provides
//    fun providesNoteAPI(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): NoteAPI {
//        return retrofitBuilder.client(okHttpClient).build().create(NoteAPI::class.java)
//    }


}