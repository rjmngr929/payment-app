package com.example.paymentapplication.utils.Validations

import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.example.paymentapplication.utils.invisible
import com.example.paymentapplication.utils.visible


fun EditText.validateRule(
    rules: List<ValidationRule>,
    errTextView: TextView ,
//    callback: ((TextInputEditTextUIModel) -> Unit)? = null
    ) // :Boolean
{

//    val textInputLayout = this.parent.parent as TextInputLayout
//    val viewEnabled = textInputLayout.isEnabled

//    this.doAfterTextChanged {
//        textInputLayout.error = null
//        textInputLayout.isErrorEnabled = false
//    }

    this.doOnTextChanged { text, start, before, count ->
        val validateText = this.text.toString().trim()

        for (i in rules.indices) {
            val isNotValid = rules[i].predicate.test(validateText)
            val message = rules[i].errorMessage

            errTextView.visible()
            if (isNotValid) {
                errTextView.text = message
                return@doOnTextChanged
            } else {
                errTextView.text = "true"
                errTextView.invisible()
            }
        }

    }

//    val validateText = this.text.toString().trim()
//    for (i in rules.indices) {
//        val isNotValid = rules[i].predicate.test(validateText)
//        val message = rules[i].errorMessage
//        if (isNotValid) {
//            if(!viewEnabled) textInputLayout.isEnabled = true
//            textInputLayout.error = "xyz error"
//            callback?.invoke(TextInputEditTextUIModel(isEnabled = viewEnabled))
//        return false
//        } else {
//            continue
//        }
//    }
//    return true

}