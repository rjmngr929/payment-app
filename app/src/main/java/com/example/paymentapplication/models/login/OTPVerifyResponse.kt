package com.example.paymentapplication.models.login

import com.google.gson.annotations.SerializedName


data class OTPVerifyResponse(
    @SerializedName("message") val message: String = "",
    @SerializedName("login_data") val loginData: LoginResponse = LoginResponse(),
    @SerializedName("login_token") val userToken: String = ""
)

data class LoginResponse(
    @SerializedName("user_id") val userId: String = "",
    @SerializedName("user_name") val userName: String = "",
    @SerializedName("user_email") val userEmail: String = "",
    @SerializedName("mobile_no") val userMobile: String = "",
    @SerializedName("user_image") val userProfileImg: String = "",
    @SerializedName("wallet_balance") val walletBalance: Double? = null,
    @SerializedName("kyc_status") val kycStatus: Boolean = false,
    @SerializedName("account_status") val accountStatus: Boolean = false
)
