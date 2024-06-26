package com.example.paymentapplication.utils.Validations

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.core.util.Predicate


open class ValidationRule(
    open val errorMessage: String = "This field is required.",
    val predicate: Predicate<String>
) {

    companion object {
        const val PHONE_NUMBER_LENGTH = 10
    }
}