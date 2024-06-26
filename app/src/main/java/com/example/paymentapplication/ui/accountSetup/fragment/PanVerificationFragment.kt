package com.example.paymentapplication.ui.accountSetup.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.graphics.Bitmap
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.airbnb.lottie.utils.Utils
import com.example.paymentapplication.databinding.FragmentPanVerificationBinding
import com.example.paymentapplication.utils.AlertDialogUtility
import com.example.paymentapplication.utils.Helper
import com.example.paymentapplication.utils.Helper.Companion.getBitmapFromUri
import com.example.paymentapplication.utils.Validations.EmptyTextRule
import com.example.paymentapplication.utils.Validations.PanCardRule
import com.example.paymentapplication.utils.Validations.validateRule
import com.example.paymentapplication.utils.showToast
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PanVerificationFragment : Fragment() {

    companion object{
        private const val TAG = "Pan Verification Fragment"
    }

    private lateinit var binding: FragmentPanVerificationBinding
    private lateinit var myContext: Context

    @Inject
    lateinit var alertDialogService: AlertDialogUtility

    private lateinit var scannerLauncher: ActivityResultLauncher<IntentSenderRequest>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPanVerificationBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scannerLauncher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
                handleActivityResult(result)
            }

        binding.panScanBtn.setOnClickListener {
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
                            scannerLauncher.launch(IntentSenderRequest.Builder(intentSender).build())
                        }
                        .addOnFailureListener() { e: Exception ->
                            e.message?.let { Log.e("error", it) }
                        }
                }

            }catch (e:Exception){
                e.stackTrace
            }
        }

        binding.pannumberEdittext.validateRule(
            rules = listOf(
                EmptyTextRule(),
                PanCardRule()
            ),
            binding.pannumberErr
        )

        binding.submitPanBtn.setOnClickListener {
            if(binding.pannumberErr.text.toString().toBoolean()){

            }
        }

    }

    private fun handleActivityResult(activityResult: ActivityResult) {
        try {
            val resultCode = activityResult.resultCode
            val result = GmsDocumentScanningResult.fromActivityResultIntent(activityResult.data)
            if (resultCode == Activity.RESULT_OK && result != null) {

                result.pages?.let { pages ->
                    for (page in pages) {
                        val imageUri = pages[0].imageUri
                        binding.panDoc.setImageURI(imageUri)
                        Helper.getBitmapFromUri(myContext, imageUri)?.let { processImage(it) }
                    }
                }

//                result.pdf?.uri?.path?.let { path ->
//                    val externalUri = FileProvider.getUriForFile(myContext, myContext.packageName + ".provider", File(path))
//                    val shareIntent =
//                        Intent(Intent.ACTION_SEND).apply {
//                            putExtra(Intent.EXTRA_STREAM, externalUri)
//                            type = "application/pdf"
//                            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//                        }
//                    startActivity(Intent.createChooser(shareIntent, "share pdf"))
//                }
            }
        }catch (e:Exception){
            e.stackTrace
        }
    }

    private fun processImage(bitmapData: Bitmap) {
        val inputImage = InputImage.fromBitmap(bitmapData, 0)
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        recognizer.process(inputImage)
            .addOnSuccessListener { result: Text ->
                val text = result.text
                if (!TextUtils.isEmpty(text)) {
                   val name = Helper.getWordAfterKeyword(text,"Name")
                   val panNumber = Helper.fetchPanCardNumber(text)
                   val fatherName = Helper.getWordAfterKeyword(text,"Father's Name")
                    Log.d(TAG, "processImage: text extracted data => $text")
                    Log.d(TAG, "processImage: text extracted data => name = $name \n pan card number = $panNumber \n and father's name = $fatherName")
                    if(panNumber != null){
                        binding.pannumberEdittext.setText(panNumber)
                    }
//                    showAlertDialog(
//                        "Text Recognized",
//                        text,
//                        false,
//                        "Copy", { _, _ ->
//                            run {
//                                binding.progress.visibility = View.GONE
//                                binding.processImage.visibility = View.VISIBLE
//                                copyText(text)
//                            }
//                        },
//                        "Cancel", { _, _ -> hideAll() },
//                        "Close Dialog", { dialog, _ ->
//                            run {
//                                binding.progress.visibility = View.GONE
//                                binding.processImage.visibility = View.VISIBLE
//                                dialog.cancel()
//                            }
//                        }
//                    )
                } else {
                    alertDialogService.showAlert(
                        context = myContext,
                        title = "Ooof",
                        message = "No Text Detected!"
                    )
//                    showAlertDialog(
//                        "Ooof",
//                        "No Text Detected!",
//                        false,
//                        "Ok", { dialog, _ ->
//                            run {
//                                binding.progress.visibility = View.GONE
//                                binding.processImage.visibility = View.VISIBLE
//                                dialog.dismiss()
//                            }
//                        },
//                    )
                }
            }
            .addOnFailureListener { e: Exception ->
               showToast("Error occurred: ${e.message}")
            }
    }

}