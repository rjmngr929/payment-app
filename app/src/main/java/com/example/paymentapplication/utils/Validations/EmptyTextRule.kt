package com.example.paymentapplication.utils.Validations

class EmptyTextRule(
    override val errorMessage: String = "This field cannot be left blank."
): ValidationRule (predicate = {it.isNullOrEmpty() })
