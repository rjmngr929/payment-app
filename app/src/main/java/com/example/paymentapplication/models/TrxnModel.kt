package com.example.paymentapplication.models

import java.io.Serializable

data class TrxnModel(val userName: String, val trxnNumber: String, val trxnType: String) : Serializable {
}