package com.example.paymentapplication.ui.Auth.forgot.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.NavHostFragment
import com.example.paymentapplication.R
import com.example.paymentapplication.databinding.FragmentSelectContactVerifyBinding


class SelectContactVerifyFragment : Fragment() {

    companion object{
        private val TAG = "Select Contact Verify Fragment"
    }

    private lateinit var binding: FragmentSelectContactVerifyBinding

    private lateinit var myContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectContactVerifyBinding.inflate(inflater, container, false)

        startAnim()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.selectSmsBtn.setOnClickListener {
            binding.selectSmsBtn.background = resources.getDrawable(R.drawable.edittext_border, null)
            binding.selectMailBtn.background = resources.getDrawable(R.drawable.edittext_border_gray, null)
        }

        binding.selectMailBtn.setOnClickListener {
            binding.selectSmsBtn.background = resources.getDrawable(R.drawable.edittext_border_gray, null)
            binding.selectMailBtn.background = resources.getDrawable(R.drawable.edittext_border, null)
        }

        binding.continueForgotBtn.setOnClickListener {
            NavHostFragment.findNavController(this@SelectContactVerifyFragment)
                .navigate(
                    R.id.action_selectContactVerifyFragment_to_forgotOtpVerifyFragment
                );
        }

    }

    private fun startAnim() {
        val  ttb = AnimationUtils.loadAnimation(myContext , R.anim.ttb)
        val  stb = AnimationUtils.loadAnimation(myContext , R.anim.stb)
        val  btt = AnimationUtils.loadAnimation(myContext , R.anim.btt)
        val  btt2 = AnimationUtils.loadAnimation(myContext , R.anim.btt2)



        binding.selectOtpMethodImg.startAnimation(ttb)

        binding.selectMailBtn.startAnimation(stb)
        binding.selectSmsBtn.startAnimation(stb)

        binding.selectOtpMethodText.startAnimation(stb)
        binding.continueForgotBtn.startAnimation(btt2)


    }


}