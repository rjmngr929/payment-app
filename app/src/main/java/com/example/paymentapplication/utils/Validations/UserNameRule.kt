package com.example.paymentapplication.utils.Validations

import com.example.paymentapplication.utils.isAlphabetWithSpace


class UserNameRule(
    override val errorMessage: String = "Please enter valid name"
): ValidationRule (predicate = {

    !it.isAlphabetWithSpace()

})