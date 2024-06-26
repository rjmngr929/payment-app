package com.example.paymentapplication.ui.accountSetup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountSetupViewModel @Inject constructor() : ViewModel() {



    private val _isProfileVerify = MutableLiveData<Boolean>()
    val isProfileVerify: LiveData<Boolean>
        get() = _isProfileVerify

    fun setProfileVerify(value: Boolean) {
        _isProfileVerify.value = value
    }

    private val _isPanVerify = MutableLiveData<Boolean>()
    val isPanVerify: LiveData<Boolean>
        get() = _isPanVerify

    fun setPanVerify(value: Boolean) {
        _isPanVerify.value = value
    }

}