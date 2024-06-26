package com.example.paymentapplication.utils.Validations

import com.example.paymentapplication.utils.isValidAadhaar
import com.example.paymentapplication.utils.isValidPan

class AadharCardRule(
    override val errorMessage: String = "Invalid Aadhaar number. It should be a 12-digit number."
): ValidationRule (predicate = {

    !it.isValidAadhaar()

})