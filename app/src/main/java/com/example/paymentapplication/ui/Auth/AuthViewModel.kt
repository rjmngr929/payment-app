package com.example.paymentapplication.ui.Auth


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.paymentapplication.models.Database.DataModel.User
import com.example.paymentapplication.models.login.OTPVerifyResponse
import com.example.paymentapplication.models.login.OtpResponse
import com.example.paymentapplication.models.login.OtpVerifyRequest
import com.example.paymentapplication.repository.UserRepository
import com.example.paymentapplication.repository.UserRoomDataRepository
import com.example.paymentapplication.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userRoomDataRepository: UserRoomDataRepository
): ViewModel(){

    val getAllUser = userRoomDataRepository.getAllUser().asLiveData()

    fun insertUser(userData: User){
        viewModelScope.launch (Dispatchers.IO){
            userRoomDataRepository.insertUser(userData = userData)
        }
    }

    fun updateUser(userData: User){
        viewModelScope.launch (Dispatchers.IO){
            userRoomDataRepository.updateUser(userData = userData)
        }
    }

    fun deleteUser(userData: User){
        viewModelScope.launch (Dispatchers.IO){
            userRoomDataRepository.deleteUser(userData = userData)
        }
    }


//********************** OTP Send Opertaion ******************************************
    val otpSendResponseLiveData: LiveData<NetworkResult<OtpResponse>>
        get() = userRepository.otpSendResponseLiveData


    fun sendOtp(mobileNumber: String){
        viewModelScope.launch {
            userRepository.loginUser(mobileNumber)
        }
    }

    fun reSendOtp(mobileNumber: String){
        viewModelScope.launch {
            userRepository.resendOtpLoginUser(mobileNumber)
        }
    }
//********************** OTP Send Opertaion ******************************************

    fun clearOtpSendRes(){
        userRepository._otpSendResponseLiveData.postValue(NetworkResult.Empty())
    }

//********************** OTP Verify Opertaion ******************************************
    val otpVerifyResponseLiveData: LiveData<NetworkResult<OTPVerifyResponse>>
        get() = userRepository.otpVerifyResponseLiveData

    fun submitOtp(otpVerifyRequest: OtpVerifyRequest){
        viewModelScope.launch {
            userRepository.verifyOtp(otpVerifyRequest)
        }
    }
//********************** OTP Verify Opertaion ******************************************

}