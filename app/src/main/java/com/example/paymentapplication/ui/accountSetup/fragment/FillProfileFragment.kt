package com.example.paymentapplication.ui.accountSetup.fragment

import android.annotation.SuppressLint
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
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.paymentapplication.R
import com.example.paymentapplication.databinding.FragmentFillProfileBinding
import com.example.paymentapplication.models.Database.DataModel.User
import com.example.paymentapplication.ui.Auth.AuthViewModel
import com.example.paymentapplication.ui.accountSetup.AccountSetupViewModel
import com.example.paymentapplication.utils.DatePickerHelper
import com.example.paymentapplication.utils.ValidationUtils
import com.example.paymentapplication.utils.Validations.EmailRule
import com.example.paymentapplication.utils.Validations.EmptyTextRule
import com.example.paymentapplication.utils.Validations.UserNameRule
import com.example.paymentapplication.utils.Validations.validateRule
import com.example.paymentapplication.utils.dismissKeyboard
import com.example.paymentapplication.utils.invisible
import com.example.paymentapplication.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class FillProfileFragment : Fragment() {

    companion object{
        private val TAG = "Fill Profile Fragment"
    }

    private lateinit var binding: FragmentFillProfileBinding

    private lateinit var myContext: Context

    lateinit var datePicker: DatePickerHelper

    private lateinit var userData: User

    private val authViewModel: AuthViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFillProfileBinding.inflate(layoutInflater)

        datePicker = DatePickerHelper(myContext)

        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.getAllUser.observe(viewLifecycleOwner, Observer {
            userData = it.first()
            it.first().apply {
                Log.d(TAG, "onViewCreated: userdata => ${this.userName}")
                binding.fullnameEdittext.setText(this.userName)
                binding.emailEdittext.setText(this.userEmail)
                binding.dobEdittext.setText(this.userDob)
                binding.phoneEdittext.setText(this.userMobile)

                Glide.with(this@FillProfileFragment)
                    .load("${this.userProfileImg}")
                    .circleCrop() // Apply circular crop to the image
//                    .placeholder(R.drawable.profile) // Placeholder image while loading
                    .error(R.drawable.dummyuser)
                    .transition(DrawableTransitionOptions.withCrossFade()) // Fade transition
                    .into(binding.profileUser)

                if(binding.dobEdittext.text.isNotEmpty()) binding.dobErr.text = "true"
            }

        })

        binding.dobEdittext.setOnClickListener {
            val cal = Calendar.getInstance()
            val d = cal.get(Calendar.DAY_OF_MONTH)
            val m = cal.get(Calendar.MONTH)
            val y = cal.get(Calendar.YEAR) - 18

            val maxDate = Calendar.getInstance()
            maxDate.add(Calendar.YEAR, -18)
            maxDate.add(Calendar.MONTH, 0)
            maxDate.add(Calendar.DATE, 0)
            datePicker.setMaxDate(maxDate.timeInMillis)

            datePicker.showDialog(d, m, y, object : DatePickerHelper.Callback {
                override fun onDateSelected(dayofMonth: Int, month: Int, year: Int) {
                    val dayStr = if (dayofMonth < 10) "0${dayofMonth}" else "${dayofMonth}"
                    val mon = month + 1
                    val monthStr = if (mon < 10) "0${mon}" else "${mon}"
                    binding.dobEdittext.setText("${year}-${monthStr}-${dayStr}")
                    binding.dobErr.text = "true"
                    binding.dobErr.invisible()
                }
            })
        }

        binding.fullnameEdittext.validateRule(
            rules = listOf(
                EmptyTextRule(),
                UserNameRule()
            ),
            binding.fullnameErr
        )

        binding.emailEdittext.validateRule(
            rules = listOf(
                EmptyTextRule(),
                EmailRule()
            ),
            binding.emailErr
        )



        binding.submitProfileBtn.setOnClickListener {
            if(binding.fullnameErr.text.toString().toBoolean() == binding.emailErr.text.toString().toBoolean() ){
                if(binding.dobErr.text.toString().toBoolean()) {
                    binding.dobErr.invisible()

                    val updateUser = userData.copy(
                        userName = binding.fullnameEdittext.text.toString(),
                        userEmail = binding.emailEdittext.text.toString(),
                        userDob = binding.dobEdittext.text.toString()
                    )

                    authViewModel.insertUser(updateUser)

                    val result = true
                    // Set the result
                    setFragmentResult("Is_Profile_Verify", Bundle().apply {
                        putBoolean("Is_Profile_Verify", result)
                    })
                    // Close the fragment (navigate back)
                    findNavController().popBackStack()
                }else{
                    binding.dobErr.visible()
                }
            }
        }

    }

//    override fun onResume() {
//        super.onResume()
//        setupListeners()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        removeListeners()
//    }
//
//
//    private fun setupListeners() {
//        binding.fullnameEdittext.addTextChangedListener(TextFieldValidation(binding.fullnameEdittext))
//        binding.emailEdittext.addTextChangedListener(TextFieldValidation(binding.emailEdittext))
//    }
//
//    private fun removeListeners() {
//        binding.fullnameEdittext.removeTextChangedListener(TextFieldValidation(binding.fullnameEdittext))
//        binding.emailEdittext.removeTextChangedListener(TextFieldValidation(binding.emailEdittext))
//    }
//
//    private fun validateName(): Boolean {
//        binding.fullnameErr.visible()
//        when {
//            binding.fullnameEdittext.text.toString().isEmpty() -> {
//                binding.fullnameErr.text =  resources.getString(R.string.filed_required)
//                return false;
//            }
//            !Patterns.PHONE.matcher(binding.fullnameEdittext.text.toString()).matches() -> {
//                binding.fullnameErr.text = resources.getString(R.string.phone_error)
//                return false;
//            }
//            binding.fullnameEdittext.text?.length != 10 -> {
//                binding.fullnameErr.text = resources.getString(R.string.phone_error)
//                return false;
//            }
//        }
//        binding.fullnameErr.invisible()
//        return true
//    }
//
//    private fun validateEmail(): Boolean {
//        binding.emailErr.visible()
//        when {
//            binding.emailEdittext.text.toString().isEmpty() -> {
//                binding.emailErr.text =  resources.getString(R.string.filed_required)
//                return false;
//            }
//            !ValidationUtils.isValidEmail(binding.emailEdittext.text.toString()) -> {
//                binding.emailErr.text = resources.getString(R.string.phone_error)
//                return false;
//            }
//        }
//        binding.emailErr.invisible()
//        return true
//    }
//
//    inner class TextFieldValidation(private val view: View) : TextWatcher {
//
//        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            when (view.id) {
//                R.id.fullname_edittext -> {
//                    validateName()
//                }
//                R.id.email_edittext -> {
////                    validateEmail()
//                }
//                R.id.dob_edittext -> {
////                    validateDOB()
//                }
//
//            }
//        }
//
//        override fun afterTextChanged(p0: Editable?) {}
//
//    }

}