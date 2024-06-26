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
import com.example.paymentapplication.databinding.FragmentReasonForAppBinding


class ReasonForAppFragment : Fragment() {

    companion object{
        private val TAG = "Reason For App Fragment"
    }

    private lateinit var binding: FragmentReasonForAppBinding

    private lateinit var myContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReasonForAppBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val toolbar: Toolbar? = (activity as? AppCompatActivity)?.findViewById(R.id.toolbar)
//        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
//        (activity as? AppCompatActivity)?.supportActionBar?.apply {
//            title = ""
//            setDisplayHomeAsUpEnabled(false)  // Disable back button if present
//            setHomeButtonEnabled(false)       // Disable the home button if present
//            setHomeAsUpIndicator(null)      // Remove the home as up indicator if present
//        }

        binding.skipBtn.setOnClickListener {

        }

        binding.continueBtn.setOnClickListener {
            NavHostFragment.findNavController(this@ReasonForAppFragment)
                .navigate(
                    R.id.action_reasonForAppFragment_to_identityOnboardingFragment
                );
        }

    }

}