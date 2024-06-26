package com.example.paymentapplication.ui.accountSetup.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.paymentapplication.R
import com.example.paymentapplication.databinding.FragmentProofIdentityBinding
import com.example.paymentapplication.ui.accountSetup.AccountSetupViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProofIdentityFragment : Fragment() {

    companion object{
        private val TAG = "Proof of Resident Fragment"
        private val REQUEST_KEY = "Is_Profile_Verify"
    }

    private lateinit var binding: FragmentProofIdentityBinding

    private val accountSetupViewModel: AccountSetupViewModel by viewModels()

    private lateinit var myContext: Context

    private var isProfileVerify = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProofIdentityBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setFragmentResultListener("Is_Profile_Verify") { requestKey, bundle ->
            // Handle the result
            isProfileVerify = bundle.getBoolean("Is_Profile_Verify", false)
        }

        binding.profileBtn.setOnClickListener {
            NavHostFragment.findNavController(this@ProofIdentityFragment)
                .navigate(
                    R.id.action_proofIdentityFragment_to_fillProfileFragment
                );
        }

        binding.panVerifyBtn.setOnClickListener {
            Log.d(TAG, "onViewCreated: viewModel live data => ${accountSetupViewModel.isProfileVerify.value}")
            if(isProfileVerify)
                NavHostFragment.findNavController(this@ProofIdentityFragment)
                    .navigate(
                        R.id.action_proofIdentityFragment_to_panVerificationFragment
                    );

        }

        binding.aadharVerifyBtn.setOnClickListener {
            NavHostFragment.findNavController(this@ProofIdentityFragment)
                .navigate(
                    R.id.action_proofIdentityFragment_to_aadharVerifyFragment
                );
        }

        binding.continueBtn.setOnClickListener {

        }

    }

}