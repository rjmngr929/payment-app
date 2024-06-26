package com.example.paymentapplication.utils.Validations

import com.example.paymentapplication.utils.isValidEmail
import com.example.paymentapplication.utils.isValidPan

class PanCardRule(
    override val errorMessage: String = "Please enter valid pan number"
): ValidationRule (predicate = {

    !it.isValidPan()

})