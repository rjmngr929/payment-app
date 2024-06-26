package com.example.paymentapplication.models.login

import retrofit2.http.Field

data class OtpVerifyRequest (
    @Field("mobile_no") val mobileNumber: String,
    @Field("otp") val otpCode: String,
    @Field("firebase_token") val fcmToken: String,
    @Field("device_name") val deviceName: String,
    @Field("device_model") val deviceModel: String,
    @Field("device_id") val deviceId: String,
    @Field("andriod_version") val androidVersion: String
)

fun OtpVerifyRequest.toMap(): Map<String, String> {
    return mapOf(
        "mobile_no" to this.mobileNumber,
        "otp" to this.otpCode,
        "firebase_token" to this.fcmToken,
        "device_name" to this.deviceName,
        "device_model" to this.deviceModel,
        "device_id" to this.deviceId,
        "andriod_version" to this.androidVersion
    )
}