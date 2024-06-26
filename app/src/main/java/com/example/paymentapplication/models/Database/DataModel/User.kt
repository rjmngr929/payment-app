package com.example.paymentapplication.models.Database.DataModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val uid: String = "",
    var userId: String,
    var userName: String,
    var userEmail: String,
    var userMobile: String,
    var userDob: String = "select DOB",
    var userProfileImg: String,
    var walletBalance: Double,
    var kycStatus: Boolean,
    var accountStatus: Boolean,

){
//    fun copyWith(
//        userId: String? = null,
//        userName: String? = null,
//        userEmail: String? = null,
//        userMobile: String? = null,
//        userProfileImg: String? = null,
//        walletBalance: Double? = null,
//        kycStatus: Boolean? = null,
//        accountStatus: Boolean? = null
//    ) = User(
//        userId = userId ?: this.userId,
//        userName = userName ?: this.userName,
//        userEmail = userEmail ?: this.userEmail,
//        userMobile = userMobile ?: this.userMobile,
//        userProfileImg = userProfileImg ?: this.userProfileImg,
//        walletBalance = walletBalance ?: this.walletBalance,
//        kycStatus = kycStatus ?: this.kycStatus,
//        accountStatus = accountStatus ?: this.accountStatus
//    )
}
