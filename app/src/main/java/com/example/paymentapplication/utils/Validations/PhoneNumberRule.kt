package com.example.paymentapplication.utils.Validations

class PhoneNumberRule(
    override val errorMessage: String = "The phone number must be 10 digits"
): ValidationRule (predicate = {

    val number = Regex (  "[^0-9]").replace(it, "")
    number.length != PHONE_NUMBER_LENGTH

})