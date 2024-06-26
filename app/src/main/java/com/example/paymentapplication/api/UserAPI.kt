package com.example.paymentapplication.api

import com.example.paymentapplication.models.login.OTPVerifyResponse
import com.example.paymentapplication.models.login.OtpResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UserAPI {

    @GET("test")
    suspend fun checkApi() : Response<OtpResponse>

    @FormUrlEncoded
    @POST("send-otp")
    suspend fun sendOtp(@Field("mobile_no") mobile_no: String) : Response<OtpResponse>

    @FormUrlEncoded
    @POST("re-send-otp")
    suspend fun reSendOtp(@Field("mobile_no") mobile_no: String) : Response<OtpResponse>

    @FormUrlEncoded
    @POST("verify-otp")
    suspend fun verifyOtp(@FieldMap fields: Map<String, String>) : Response<OTPVerifyResponse>


}