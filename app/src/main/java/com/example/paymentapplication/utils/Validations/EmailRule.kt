package com.example.paymentapplication.utils.Validations

import com.example.paymentapplication.utils.isValidEmail

class EmailRule(
    override val errorMessage: String = "Please enter valid email"
): ValidationRule (predicate = {

    !it.isValidEmail()

})