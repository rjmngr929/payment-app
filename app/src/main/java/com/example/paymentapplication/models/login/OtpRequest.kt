package com.example.paymentapplication.models.login

import com.google.gson.annotations.SerializedName

data class OtpRequest(@SerializedName("mobile_no") var mobileNo: String)
