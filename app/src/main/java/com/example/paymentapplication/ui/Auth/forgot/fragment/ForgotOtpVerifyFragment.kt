package com.example.paymentapplication.ui.Auth.forgot.fragment

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.paymentapplication.R
import com.example.paymentapplication.databinding.FragmentForgotOtpVerifyBinding

class ForgotOtpVerifyFragment : Fragment() {

    companion object{
        private val TAG = "OTP Verify Fragment"
    }

    private lateinit var binding: FragmentForgotOtpVerifyBinding

    private lateinit var myContext: Context

    private  var otp_code : String = ""

    private lateinit var firstField : EditText
    private lateinit var secondField : EditText
    private lateinit var thirdField : EditText
    private lateinit var forthField : EditText
    private lateinit var fifthField : EditText
    private lateinit var sixthField : EditText

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotOtpVerifyBinding.inflate(inflater, container, false)

        resendTimer()

        //GenericTextWatcher here works only for moving to next EditText when a number is entered
        //first parameter is the current EditText and second parameter is next EditText
        binding.otpOne.addTextChangedListener(GenericTextWatcher(binding.otpOne, binding.otpTwo))
        binding.otpTwo.addTextChangedListener(GenericTextWatcher(binding.otpTwo, binding.otpThree))
        binding.otpThree.addTextChangedListener(GenericTextWatcher(binding.otpThree, binding.otpFour))
        binding.otpFour.addTextChangedListener(GenericTextWatcher(binding.otpFour, binding.otpFive))
        binding.otpFive.addTextChangedListener(GenericTextWatcher(binding.otpFive, binding.otpSix))
        binding.otpSix.addTextChangedListener(GenericTextWatcher(binding.otpSix, null))

        //GenericKeyEvent here works for deleting the element and to switch back to previous EditText
        //first parameter is the current EditText and second parameter is previous EditText
        binding.otpOne.setOnKeyListener(GenericKeyEvent(binding.otpOne, null))
        binding.otpTwo.setOnKeyListener(GenericKeyEvent(binding.otpTwo, binding.otpOne))
        binding.otpThree.setOnKeyListener(GenericKeyEvent(binding.otpThree, binding.otpTwo))
        binding.otpFour.setOnKeyListener(GenericKeyEvent(binding.otpFour,binding.otpThree))
        binding.otpFive.setOnKeyListener(GenericKeyEvent(binding.otpFive,binding.otpFour))
        binding.otpSix.setOnKeyListener(GenericKeyEvent(binding.otpSix,binding.otpFive))


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        firstField = binding.otpOne
        secondField = binding.otpTwo
        thirdField = binding.otpThree
        forthField = binding.otpFour
        fifthField = binding.otpFive
        sixthField = binding.otpSix

        binding.resendOtpBtn.setOnClickListener {
            this@ForgotOtpVerifyFragment.otp_code = ""
            firstField.text.clear()
            secondField.text.clear()
            thirdField.text.clear()
            forthField.text.clear()
            fifthField.text.clear()
            sixthField.text.clear()
            firstField.requestFocus()
            resendTimer()
//            if(fromRegister){
//                resendregisterOtp(
//                    name,
//                    email,
//                    mobile,
//                    country_code,
//                    refer_code
//                )
//            }else{
//                resendLoginOtp()
//            }
        }

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
                    otp_code = firstField.text.toString()

                }
                R.id.otp_two -> if (text.length == 1) {
                    nextView!!.requestFocus()
                    otp_code += secondField.text.toString()

                }
                R.id.otp_three -> if (text.length == 1) {
                    nextView!!.requestFocus()
                    otp_code += thirdField.text.toString()
                }
                R.id.otp_four -> if (text.length == 1) {
                    nextView!!.requestFocus()
                    otp_code += forthField.text.toString()
                }
                R.id.otp_five -> if (text.length == 1) {
                    nextView!!.requestFocus()
                    otp_code += fifthField.text.toString()
                }
                R.id.otp_six -> if (text.length == 1) {
                    otp_code = binding.otpOne.text.toString() + binding.otpTwo.text.toString() + binding.otpThree.text.toString() + binding.otpFour.text.toString() + binding.otpFive.text.toString() + binding.otpSix.text.toString()
                    if(otp_code.length == 6){
//                        if(fromRegister){
//                            registerVerfyUser(otp_code)
//                        }else{
//                            loginVerfyUser()
//                        }
                        hideKeyboard((myContext as Activity).currentFocus ?: View(myContext))
                    }else{
                        Toast.makeText(context, "please enter valid otp", Toast.LENGTH_SHORT).show()
                    }
                }
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
        val inputMethodManager = myContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun resendTimer(){

        object : CountDownTimer(59000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
//                TimerText.setText("00 : " + millisUntilFinished / 1000)
                binding.resendOtpBtn.visibility = View.GONE
                binding.timerText.visibility = View.VISIBLE
                binding.timerText.text = if(millisUntilFinished / 1000 < 10) String.format("Resend code in 0%s s",(millisUntilFinished / 1000))  else  String.format("Resend code in %s s",(millisUntilFinished / 1000)) //Auto fetching OTP 10sec
            }

            override fun onFinish() {
                binding.resendOtpBtn.visibility = View.VISIBLE
                binding.timerText.visibility = View.GONE
            }
        }.start()
    }


}