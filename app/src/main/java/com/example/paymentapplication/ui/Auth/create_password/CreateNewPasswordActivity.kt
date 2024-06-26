package com.example.paymentapplication.ui.Auth.create_password

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import com.example.paymentapplication.R
import com.example.paymentapplication.databinding.ActivityCreateNewPasswordBinding
import com.example.paymentapplication.ui.Auth.forgot.fragment.SelectContactVerifyFragment

class CreateNewPasswordActivity : AppCompatActivity() {

    companion object{
        private val TAG = "Create New Password"
    }

    private lateinit var binding: ActivityCreateNewPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startAnim()

        val toolbar = binding.createNewPassToolbar.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Create New Password"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startAnim() {
        val  ttb = AnimationUtils.loadAnimation(this , R.anim.ttb)
        val  stb = AnimationUtils.loadAnimation(this , R.anim.stb)
        val  btt = AnimationUtils.loadAnimation(this , R.anim.btt)
        val  btt2 = AnimationUtils.loadAnimation(this , R.anim.btt2)



        binding.selectOtpMethodImg.startAnimation(ttb)

        binding.selectOtpMethodImg.startAnimation(ttb)
        binding.passwordEdittext.startAnimation(stb)
        binding.confirmPasswordEdittext.startAnimation(stb)

        binding.rememberCheckbox.startAnimation(btt)
        binding.continueResetBtn.startAnimation(btt2)


    }


}