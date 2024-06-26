package com.example.paymentapplication.ui.createNewPin

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.paymentapplication.R
import com.example.paymentapplication.databinding.ActivityCreateNewPinBinding
import com.example.paymentapplication.ui.Auth.forgot.fragment.ForgotOtpVerifyFragment

class CreateNewPinActivity : AppCompatActivity() {

    companion object{
        private val TAG = "Activity Create Pin"
    }

    private lateinit var binding: ActivityCreateNewPinBinding

    private  var pinCode : String = ""

    private lateinit var firstField : EditText
    private lateinit var secondField : EditText
    private lateinit var thirdField : EditText
    private lateinit var forthField : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewPinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firstField = binding.otpOne
        secondField = binding.otpTwo
        thirdField = binding.otpThree
        forthField = binding.otpFour

        //GenericTextWatcher here works only for moving to next EditText when a number is entered
        //first parameter is the current EditText and second parameter is next EditText
        firstField.addTextChangedListener(GenericTextWatcher(binding.otpOne, binding.otpTwo))
        secondField.addTextChangedListener(GenericTextWatcher(binding.otpTwo, binding.otpThree))
        thirdField.addTextChangedListener(GenericTextWatcher(binding.otpThree, binding.otpFour))
        forthField.addTextChangedListener(GenericTextWatcher(binding.otpFour, null))

        //GenericKeyEvent here works for deleting the element and to switch back to previous EditText
        //first parameter is the current EditText and second parameter is previous EditText
        firstField.setOnKeyListener(ForgotOtpVerifyFragment.GenericKeyEvent(binding.otpOne, null))
        secondField.setOnKeyListener(
            ForgotOtpVerifyFragment.GenericKeyEvent(
                secondField,
                firstField
            )
        )
        thirdField.setOnKeyListener(
            ForgotOtpVerifyFragment.GenericKeyEvent(
                thirdField,
                secondField
            )
        )
        forthField.setOnKeyListener(
            ForgotOtpVerifyFragment.GenericKeyEvent(
                forthField,
                thirdField
            )
        )



    }

    class GenericKeyEvent internal constructor(private val currentView: EditText, private val previousView: EditText?) : View.OnKeyListener{
        override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {
            if(event!!.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && currentView.id != R.id.otp_one && currentView.text.isEmpty()) {
                //If current is empty then previous EditText's number will also be deleted
                previousView!!.text = null
                previousView.requestFocus()
                return true
            }
            return false
        }


    }

    inner class GenericTextWatcher internal constructor(private val currentView: View, private val nextView: View?) :
        TextWatcher {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun afterTextChanged(editable: Editable) { // TODO Auto-generated method stub
            val text = editable.toString()
            when (currentView.id) {
                R.id.otp_one -> if (text.length == 1) {
                    nextView!!.requestFocus()
                    pinCode = firstField.text.toString()

                }
                R.id.otp_two -> if (text.length == 1) {
                    nextView!!.requestFocus()
                    pinCode += secondField.text.toString()

                }
                R.id.otp_three -> if (text.length == 1) {
                    nextView!!.requestFocus()
                    pinCode += thirdField.text.toString()
                }
                R.id.otp_four -> if (text.length == 1) {
                    pinCode += forthField.text.toString()

                    hideKeyboard(currentFocus ?: View(this@CreateNewPinActivity))
                    forthField.clearFocus()
                }
//                R.id.otp_five -> if (text.length == 1) {
//                    nextView!!.requestFocus()
//                    otp_code += fifthField.text.toString()
//                }
//                R.id.otp_six -> if (text.length == 1) {
//                    otp_code = binding.otpOne.text.toString() + binding.otpTwo.text.toString() + binding.otpThree.text.toString() + binding.otpFour.text.toString() + binding.otpFive.text.toString() + binding.otpSix.text.toString()
//                    if(otp_code.length == 6){
////                        if(fromRegister){
////                            registerVerfyUser(otp_code)
////                        }else{
////                            loginVerfyUser()
////                        }
//                        hideKeyboard((myContext as Activity).currentFocus ?: View(myContext))
//                    }else{
//                        Toast.makeText(context, "please enter valid otp", Toast.LENGTH_SHORT).show()
//                    }
//                }
                //You can use EditText4 same as above to hide the keyboard
            }
        }

        override fun beforeTextChanged(
            arg0: CharSequence,
            arg1: Int,
            arg2: Int,
            arg3: Int
        ) { // TODO Auto-generated method stub
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onTextChanged(
            arg0: CharSequence,
            arg1: Int,
            arg2: Int,
            arg3: Int
        ) {
        }

    }

    fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


}