package com.example.paymentapplication.ui.Auth.login.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.example.paymentapplication.R
import com.example.paymentapplication.databinding.FragmentEnterMobileBinding
import com.example.paymentapplication.ui.Auth.AuthViewModel
import com.example.paymentapplication.utils.AlertDialogUtility
import com.example.paymentapplication.utils.NetworkResult
import com.example.paymentapplication.utils.bottomtotop_type_oneAnimation
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
class EnterMobileFragment : Fragment() {

    companion object{
        private const val TAG = "Enter Mobile Fragment"
    }

    private lateinit var binding: FragmentEnterMobileBinding
    private lateinit var myContext: Context

    private val authViewModel: AuthViewModel by viewModels()

    private lateinit var loader: AlertDialog

    @Inject
    lateinit var alertDialogService: AlertDialogUtility

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterMobileBinding.inflate(inflater, container, false)

//       Animate the components
        binding.createAccText.toptobottomAnimation(myContext)
        binding.enterMobileEdittext.zoomOutAnimation(myContext)
        binding.sendOtpBtn.bottomtotop_type_oneAnimation(myContext)

        loader = getLoadingDialog(myContext)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sendOtpBtn.setOnClickListener {
            if(validateMobile()) {
                binding.enterMobileEdittext.dismissKeyboard()
                authViewModel.sendOtp(binding.enterMobileEdittext.text.toString())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setupListeners()
        bindObservers()
    }

    override fun onPause() {
        super.onPause()
        removeListeners()
    }

    private fun bindObservers() {
        authViewModel.otpSendResponseLiveData.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "bindObservers: response received from otp send => ${it}")
            when (it) {
                is NetworkResult.Success -> {
                    hideLoader(myContext, loader)
                    Log.d(TAG, "bindObservers: response received from otp send => ${it}")

                    authViewModel.clearOtpSendRes()

                    val bundle = Bundle()
                    bundle.putString("mobile_no", binding.enterMobileEdittext.text.toString())
                    NavHostFragment.findNavController(this@EnterMobileFragment)
                        .navigate(
                            R.id.action_enterMobileFragment_to_otpVerifyFragment,
                            bundle
                        );
                }
                is NetworkResult.Error -> {
                    hideLoader(myContext, loader)

                    alertDialogService.alertDialogAnim(
                        myContext,
                        it.message.toString(),
                        R.raw.failed
                    )
                    Log.d(TAG, "bindObservers: response received from otp send => Error = ${it.message}")
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

    private fun setupListeners() {
        binding.enterMobileEdittext.addTextChangedListener(TextFieldValidation(binding.enterMobileEdittext))
    }

    private fun removeListeners() {
        binding.enterMobileEdittext.removeTextChangedListener(TextFieldValidation(binding.enterMobileEdittext))
    }

    private fun validateMobile(): Boolean {
        binding.mobileErr.visible()
        when {
            binding.enterMobileEdittext.text.toString().isEmpty() -> {
                binding.mobileErr.text =  resources.getString(R.string.filed_required)
                return false;
            }
            !Patterns.PHONE.matcher(binding.enterMobileEdittext.text.toString()).matches() -> {
                binding.mobileErr.text = resources.getString(R.string.phone_error)
                return false;
            }
            binding.enterMobileEdittext.text?.length != 10 -> {
                binding.mobileErr.text = resources.getString(R.string.phone_error)
                return false;
            }
            binding.enterMobileEdittext.text?.length == 10 -> {
                binding.mobileErr.invisible()
//                Call api after enter mobile number
                binding.enterMobileEdittext.dismissKeyboard()
                authViewModel.sendOtp(binding.enterMobileEdittext.text.toString())
            }
        }
        binding.mobileErr.invisible()
        return true
    }

    inner class TextFieldValidation(private val view: View) : TextWatcher {

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            when (view.id) {
                R.id.enterMobile_edittext -> {
                    validateMobile()
                }

            }
        }

        override fun afterTextChanged(p0: Editable?) {}

    }

}