package com.example.paymentapplication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.paymentapplication.api.UserAPI
import com.example.paymentapplication.di.exception.NetworkExceptionHandler
import com.example.paymentapplication.models.Database.Dao.UserDao
import com.example.paymentapplication.models.Database.DataModel.User
import com.example.paymentapplication.models.login.OTPVerifyResponse
import com.example.paymentapplication.models.login.OtpResponse
import com.example.paymentapplication.models.login.OtpVerifyRequest
import com.example.paymentapplication.models.login.toMap
import com.example.paymentapplication.utils.NetworkResult
import okio.IOException
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor( private val userAPI: UserAPI, private val exceptionHandler: NetworkExceptionHandler) {


//********************** OTP Send Opertaion ******************************************
    val _otpSendResponseLiveData = MutableLiveData<NetworkResult<OtpResponse>>()
    val otpSendResponseLiveData: LiveData<NetworkResult<OtpResponse>>
        get() = _otpSendResponseLiveData

    suspend fun loginUser(mobileNumberRequest: String) {
        _otpSendResponseLiveData.postValue(NetworkResult.Loading())
        try {
            val response =userAPI.sendOtp(mobileNumberRequest)
            if (response.isSuccessful) {
                handleOtpSendResponse(response)
            } else {
                try {
                    _otpSendResponseLiveData.postValue(NetworkResult.Error(response.errorBody()?.string()
                        ?.let { JSONObject(it).getString("message") }))
                }catch (e: Exception){
                    Log.d("TAG", "loginUser: error on catch part for loginUser")
                }
            }

        }catch (e: Exception){
            _otpSendResponseLiveData.postValue(NetworkResult.Error(exceptionHandler.handleException(e)))
        }
    }

    suspend fun resendOtpLoginUser(mobileNumberRequest: String) {
        _otpSendResponseLiveData.postValue(NetworkResult.Loading())
        try {
            val response =userAPI.reSendOtp(mobileNumberRequest)
            if (response.isSuccessful) {
                handleOtpSendResponse(response)
            } else {
                try {
                    _otpSendResponseLiveData.postValue(NetworkResult.Error(response.errorBody()?.string()
                        ?.let { JSONObject(it).getString("message") }))
                }catch (e: Exception){
                    Log.d("TAG", "loginUser: error on catch part for loginUser")
                }
            }
        }catch (e: Exception){
            _otpSendResponseLiveData.postValue(NetworkResult.Error(exceptionHandler.handleException(e)))
        }
    }

    private fun handleOtpSendResponse(response: Response<OtpResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _otpSendResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else if(response.errorBody()!=null){
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _otpSendResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        }
        else{
            _otpSendResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
//********************** OTP Send Opertaion ******************************************





//********************** OTP Verify Opertaion ******************************************
    private val _otpVerifyResponseLiveData = MutableLiveData<NetworkResult<OTPVerifyResponse>>()
    val otpVerifyResponseLiveData: LiveData<NetworkResult<OTPVerifyResponse>>
        get() = _otpVerifyResponseLiveData

    suspend fun verifyOtp(otpVerifyRequest: OtpVerifyRequest) {
        _otpVerifyResponseLiveData.postValue(NetworkResult.Loading())
        try {
            val response =userAPI.verifyOtp(otpVerifyRequest.toMap())
            if (response.isSuccessful) {
                handleVerifyOtpResponse(response)
            } else {
                try {
                    _otpVerifyResponseLiveData.postValue(NetworkResult.Error(response.errorBody()?.string()
                        ?.let { JSONObject(it).getString("message") }))
                }catch (e: Exception){
                    Log.d("TAG", "loginUser: error on catch part for loginUser")
                }
            }
        }catch (e: Exception){
            _otpVerifyResponseLiveData.postValue(NetworkResult.Error(exceptionHandler.handleException(e)))
        }
    }

    private fun handleVerifyOtpResponse(response: Response<OTPVerifyResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _otpVerifyResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else if(response.errorBody()!=null){
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _otpVerifyResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        }
        else{
            _otpVerifyResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
//********************** OTP Verify Opertaion ******************************************


}