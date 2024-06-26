package com.example.paymentapplication.ui.accountSetup.fragment

import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.paymentapplication.R
import com.example.paymentapplication.databinding.FragmentAadharVerifyBinding
import com.example.paymentapplication.utils.Validations.AadharCardRule
import com.example.paymentapplication.utils.Validations.EmptyTextRule
import com.example.paymentapplication.utils.Validations.validateRule
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult


class AadharVerifyFragment : Fragment() {

    companion object{
        private const val TAG = "Aadhar Verify Fragment"
    }

    private lateinit var binding: FragmentAadharVerifyBinding

    private lateinit var myContext: Context

    private lateinit var frontBitmap: Bitmap
    private lateinit var backBitmap: Bitmap

    private lateinit var aadharFrontScannerLauncher: ActivityResultLauncher<IntentSenderRequest>
    private lateinit var aadharBackScannerLauncher: ActivityResultLauncher<IntentSenderRequest>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentAadharVerifyBinding.inflate(inflater, container, false)

        binding.aadharNumberEdittext.validateRule(
            rules = listOf(
                EmptyTextRule(),
                AadharCardRule()
            ),
            binding.aadharNumberErr
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aadharFrontScannerLauncher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            handleAadharFrontActivityResult(result)
        }

        aadharBackScannerLauncher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            handleAadharBackActivityResult(result)
        }

        binding.aadharFrontScanBtn.setOnClickListener {
            try {
                val options = GmsDocumentScannerOptions.Builder()
                    .setScannerMode(GmsDocumentScannerOptions.CAPTURE_MODE_MANUAL)
                    .setResultFormats(GmsDocumentScannerOptions.RESULT_FORMAT_JPEG)
                    .setGalleryImportAllowed(false)
                    .setScannerMode(GmsDocumentScannerOptions.SCANNER_MODE_FULL)
                    .setPageLimit(1)

                activity?.let { it1 ->
                    GmsDocumentScanning.getClient(options.build())
                        .getStartScanIntent(it1)
                        .addOnSuccessListener { intentSender: IntentSender ->
                            aadharFrontScannerLauncher.launch(IntentSenderRequest.Builder(intentSender).build())
                        }
                        .addOnFailureListener() { e: Exception ->
                            e.message?.let { Log.e("error", it) }
                        }
                }

            }catch (e:Exception){
                e.stackTrace
            }
        }

        binding.aadharbackScanBtn.setOnClickListener {
            try {
                val options = GmsDocumentScannerOptions.Builder()
                    .setScannerMode(GmsDocumentScannerOptions.CAPTURE_MODE_MANUAL)
                    .setResultFormats(GmsDocumentScannerOptions.RESULT_FORMAT_JPEG)
                    .setGalleryImportAllowed(false)
                    .setScannerMode(GmsDocumentScannerOptions.SCANNER_MODE_FULL)
                    .setPageLimit(1)

                activity?.let { it1 ->
                    GmsDocumentScanning.getClient(options.build())
                        .getStartScanIntent(it1)
                        .addOnSuccessListener { intentSender: IntentSender ->
                            aadharBackScannerLauncher.launch(IntentSenderRequest.Builder(intentSender).build())
                        }
                        .addOnFailureListener() { e: Exception ->
                            e.message?.let { Log.e("error", it) }
                        }
                }

            }catch (e:Exception){
                e.stackTrace
            }
        }

    }

    private fun handleAadharFrontActivityResult(activityResult: ActivityResult) {
        try {
            val resultCode = activityResult.resultCode
            val result = GmsDocumentScanningResult.fromActivityResultIntent(activityResult.data)
            if (resultCode == Activity.RESULT_OK && result != null) {

                result.pages?.let { pages ->
                    for (page in pages) {
                        val imageUri = pages[0].imageUri
                        binding.aadharFrontDoc.setImageURI(imageUri)
                    }
                }

            }
        }catch (e:Exception){
            e.stackTrace
        }
    }

    private fun handleAadharBackActivityResult(activityResult: ActivityResult) {
        try {
            val resultCode = activityResult.resultCode
            val result = GmsDocumentScanningResult.fromActivityResultIntent(activityResult.data)
            if (resultCode == Activity.RESULT_OK && result != null) {

                result.pages?.let { pages ->
                    for (page in pages) {
                        val imageUri = pages[0].imageUri
                        binding.aadharBackDoc.setImageURI(imageUri)
                    }
                }

            }
        }catch (e:Exception){
            e.stackTrace
        }
    }

}