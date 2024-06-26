package com.example.paymentapplication.ui.Auth.login.fragments

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.paymentapplication.R
import com.example.paymentapplication.databinding.FragmentOtpVerifyBinding
import com.example.paymentapplication.models.Database.DataModel.User
import com.example.paymentapplication.models.login.OtpVerifyRequest
import com.example.paymentapplication.ui.Auth.AuthViewModel
import com.example.paymentapplication.ui.Auth.forgot.fragment.ForgotOtpVerifyFragment
import com.example.paymentapplication.ui.dashboard.MainActivity
import com.example.paymentapplication.utils.AlertDialogUtility
import com.example.paymentapplication.utils.NetworkResult
import com.example.paymentapplication.utils.TokenManager
import com.example.paymentapplication.utils.bottomtotop_type_oneAnimation
import com.example.paymentapplication.utils.bottomtotop_type_twoAnimation
import com.example.paymentapplication.utils.dismissKeyboard
import com.example.paymentapplication.utils.getLoadingDialog
import com.example.paymentapplication.utils.hideLoader
import com.example.paymentapplication.utils.invisible
import com.example.paymentapplication.utils.showLoader
import com.example.paymentapplication.utils.toptobottomAnimation
import com.example.paymentapplication.utils.visible
import com.example.paymentapplication.utils.zoomOutAnimation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OtpVerifyFragment : Fragment() {

    companion object{
        private const val TAG = "OTP Verify Fragment"
    }

    @Inject
    lateinit var tokenManager: TokenManager

    @Inject
    lateinit var alertDialogService: AlertDialogUtility

    private lateinit var binding: FragmentOtpVerifyBinding
    private lateinit var myContext: Context

    private val authViewModel: AuthViewModel by viewModels()

    private lateinit var loader: AlertDialog

    private  var mobileNumber : String = ""
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
        binding = FragmentOtpVerifyBinding.inflate(inflater, container, false)

        loader = getLoadingDialog(myContext)

        firstField = binding.otpOne
        secondField = binding.otpTwo
        thirdField = binding.otpThree
        forthField = binding.otpFour
        fifthField = binding.otpFive
        sixthField = binding.otpSix

        binding.enterOtpText.toptobottomAnimation(myContext)
        binding.otpSendNumberLabel.toptobottomAnimation(myContext)
        binding.otpLayout.zoomOutAnimation(myContext)
        binding.timerText.bottomtotop_type_oneAnimation(myContext)
        binding.resendOtpBtn.bottomtotop_type_twoAnimation(myContext)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments.let{
            if (it != null) {
                mobileNumber = it.getString("mobile_no", null).toString()
            }

        }

        resendOtpbindObservers()

        verifyOtpbindObservers()

        resendTimer()

        binding.otpSendNumberLabel.text = String.format("Code has been send to +91 %s",mobileNumber)

        //GenericTextWatcher here works only for moving to next EditText when a number is entered
        //first parameter is the current EditText and second parameter is next EditText
//        binding.otpOne.addTextChangedListener(GenericTextWatcher(binding.otpOne, binding.otpTwo))
//        binding.otpTwo.addTextChangedListener(GenericTextWatcher(binding.otpTwo, binding.otpThree))
//        binding.otpThree.addTextChangedListener(GenericTextWatcher(binding.otpThree, binding.otpFour))
//        binding.otpFour.addTextChangedListener(GenericTextWatcher(binding.otpFour, binding.otpFive))
//        binding.otpFive.addTextChangedListener(GenericTextWatcher(binding.otpFive, binding.otpSix))
//        binding.otpSix.addTextChangedListener(GenericTextWatcher(binding.otpSix, null))

        //GenericKeyEvent here works for deleting the element and to switch back to previous EditText
        //first parameter is the current EditText and second parameter is previous EditText
        binding.otpOne.setOnKeyListener(ForgotOtpVerifyFragment.GenericKeyEvent(binding.otpOne, null))
        binding.otpTwo.setOnKeyListener(
            GenericKeyEvent(
                binding.otpTwo,
                binding.otpOne
            )
        )
        binding.otpThree.setOnKeyListener(
            GenericKeyEvent(
                binding.otpThree,
                binding.otpTwo
            )
        )
        binding.otpFour.setOnKeyListener(
            GenericKeyEvent(
                binding.otpFour,
                binding.otpThree
            )
        )
        binding.otpFive.setOnKeyListener(
            GenericKeyEvent(
                binding.otpFive,
                binding.otpFour
            )
        )
        binding.otpSix.setOnKeyListener(
            GenericKeyEvent(
                binding.otpSix,
                binding.otpFive
            )
        )

        binding.resendOtpBtn.setOnClickListener {
            this@OtpVerifyFragment.otp_code = ""
            clearOtpFields()
            authViewModel.reSendOtp(mobileNumber)
        }

        binding.sumbitOtpBtn.setOnClickListener {
            val otpRequestData =  OtpVerifyRequest(
                mobileNumber,
                otp_code,
                "fcmhjdbdhjsbdhjsbsjdbdshjbdjs",
                Build.BRAND,
                Build.MODEL,
                "deviceId", //Build.ID
                Build.VERSION.RELEASE
            )
            authViewModel.submitOtp(otpRequestData)
        }
    }

    fun setupListener(){
        binding.otpOne.addTextChangedListener(GenericTextWatcher(binding.otpOne, binding.otpTwo))
        binding.otpTwo.addTextChangedListener(GenericTextWatcher(binding.otpTwo, binding.otpThree))
        binding.otpThree.addTextChangedListener(GenericTextWatcher(binding.otpThree, binding.otpFour))
        binding.otpFour.addTextChangedListener(GenericTextWatcher(binding.otpFour, binding.otpFive))
        binding.otpFive.addTextChangedListener(GenericTextWatcher(binding.otpFive, binding.otpSix))
        binding.otpSix.addTextChangedListener(GenericTextWatcher(binding.otpSix, null))
    }

    fun removeListener(){
        binding.otpOne.removeTextChangedListener(GenericTextWatcher(binding.otpOne, binding.otpTwo))
        binding.otpTwo.removeTextChangedListener(GenericTextWatcher(binding.otpTwo, binding.otpThree))
        binding.otpThree.removeTextChangedListener(GenericTextWatcher(binding.otpThree, binding.otpFour))
        binding.otpFour.removeTextChangedListener(GenericTextWatcher(binding.otpFour, binding.otpFive))
        binding.otpFive.removeTextChangedListener(GenericTextWatcher(binding.otpFive, binding.otpSix))
        binding.otpSix.removeTextChangedListener(GenericTextWatcher(binding.otpSix, null))
    }

    fun clearOtpFields(){
        firstField.text.clear()
        secondField.text.clear()
        thirdField.text.clear()
        forthField.text.clear()
        fifthField.text.clear()
        sixthField.text.clear()
        firstField.requestFocus()
    }

    override fun onResume() {
        super.onResume()
        setupListener()
    }

    override fun onPause() {
        super.onPause()
        removeListener()
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
        override fun afterTextChanged(editable: Editable) {
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

//                        hideKeyboard((myContext as Activity).currentFocus ?: View(myContext))
                        sixthField.dismissKeyboard()
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

    private fun resendTimer(){
        object : CountDownTimer(59000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
//                TimerText.setText("00 : " + millisUntilFinished / 1000)
                binding.resendOtpBtn.visibility = View.GONE
                binding.timerText.visibility = View.VISIBLE
                binding.timerText.text = if(millisUntilFinished / 1000 < 10) String.format("Resend code in 0%ss",(millisUntilFinished / 1000))  else  String.format("Resend code in %ss",(millisUntilFinished / 1000)) //Auto fetching OTP 10sec
            }
            override fun onFinish() {
                binding.resendOtpBtn.visibility = View.VISIBLE
                binding.timerText.visibility = View.GONE
            }
        }.start()
    }

    private fun resendOtpbindObservers() {
        authViewModel.otpSendResponseLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    hideLoader(myContext, loader)
                   resendTimer()
                }
                is NetworkResult.Error -> {
                    hideLoader(myContext, loader)
                    alertDialogService.alertDialogAnim(myContext, it.message.toString(), R.raw.failed)
                }
                is NetworkResult.Loading ->{
                    showLoader(myContext, loader)
                }
                is NetworkResult.Empty -> {
                    hideLoader(myContext, loader)
                }
            }
        })
    }

    private fun verifyOtpbindObservers() {
        authViewModel.otpVerifyResponseLiveData.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "verifyOtpbindObservers: verify otp data => ${it}")
            when (it) {
                is NetworkResult.Success -> {
                    hideLoader(myContext, loader)
                    binding.otpErr.invisible()
                    val userData = it.data?.loginData
                    tokenManager.saveToken(it.data?.userToken.toString())
                    authViewModel.insertUser(
                        User(
                            userId = userData?.userId.toString(),
                            userName = userData?.userName.toString(),
                            userEmail = userData?.userEmail.toString(),
                            userMobile = userData?.userMobile.toString(),
                            userProfileImg = userData?.userProfileImg.toString(),
                            walletBalance = userData?.walletBalance ?: 0.0,
                            kycStatus = userData?.kycStatus ?: false,
                            accountStatus = userData?.accountStatus ?: false
                        )
                    )
                    startActivity(Intent(myContext,MainActivity::class.java))
                    activity?.finish()
                }
                is NetworkResult.Error -> {
                    binding.otpErr.visible()
                    hideLoader(myContext, loader)
                    alertDialogService.alertDialogAnim(myContext, it.message.toString(), R.raw.failed)
                    otp_code = ""
                    clearOtpFields()
                }
                is NetworkResult.Loading ->{
                    binding.otpErr.invisible()
                    showLoader(myContext, loader)
                }
                is NetworkResult.Empty -> {
                    binding.otpErr.invisible()
                    hideLoader(myContext, loader)
                }
            }
        })
    }

}