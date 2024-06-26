package com.example.paymentapplication.ui.accountSetup.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import com.example.paymentapplication.R
import com.example.paymentapplication.databinding.FragmentIdentityOnboardingBinding


class IdentityOnboardingFragment : Fragment() {

    companion object{
        private val TAG = "Identity Onboarding Fragment"
    }

    private lateinit var binding: FragmentIdentityOnboardingBinding

    private lateinit var myContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIdentityOnboardingBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val toolbar: Toolbar? = (activity as? AppCompatActivity)?.findViewById(R.id.toolbar)
//        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
//        (activity as? AppCompatActivity)?.supportActionBar?.apply {
//            title = ""
//            setDisplayHomeAsUpEnabled(true)  // Disable back button if present
//            setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back)        // Remove the home as up indicator if present
//        }

        binding.verifyIdentityBtn.setOnClickListener {
            NavHostFragment.findNavController(this@IdentityOnboardingFragment)
                .navigate(
                    R.id.action_identityOnboardingFragment_to_proofIdentityFragment
                );
        }

    }


}