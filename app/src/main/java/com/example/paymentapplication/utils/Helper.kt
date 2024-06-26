package com.example.paymentapplication.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import org.json.JSONObject
import java.io.InputStream
import java.lang.Exception

class Helper {
    companion object {
        fun isValidEmail(email: String): Boolean {
            return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun hideKeyboard(view: View){
            try {
                val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }catch (e: Exception){

            }
        }

        private fun copyText(text: String, myContext: Context) {
            if (!TextUtils.isEmpty(text)) {
                val clipboard = myContext.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Recognized Text", text)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(myContext, "Copied to Clipboard!", Toast.LENGTH_SHORT).show()
            }
        }


        private fun getSystemDetail(): JSONObject {

            val deviceInfo = JSONObject()

            deviceInfo.put("Brand", Build.BRAND)
            deviceInfo.put("Model", Build.MODEL)
            deviceInfo.put("ID", Build.ID)
            deviceInfo.put("SDK", Build.VERSION.SDK_INT)
            deviceInfo.put("Manufacture", Build.MANUFACTURER)
            deviceInfo.put("Board", Build.BOARD)
            deviceInfo.put("version_code", Build.VERSION.RELEASE)

            if(Build.HOST != null)
                deviceInfo.put("Host", Build.HOST)
            else
                deviceInfo.put("Host", "")

//            //  Total Ram
//            val actManager = context.getSystemService(AppCompatActivity.ACTIVITY_SERVICE) as ActivityManager
//            val memInfo = ActivityManager.MemoryInfo()
//            actManager.getMemoryInfo(memInfo)
//            val availMemory = memInfo.availMem.toDouble()/(1024*1024*1024)
//            val totalMemory= memInfo.totalMem.toDouble()/(1024*1024*1024)
//
//
//            //  Total Storage
//            val iPath: File = Environment.getDataDirectory()
//            val iStat = StatFs(iPath.path)
//            val iBlockSize = iStat.blockSizeLong
//            val iAvailableBlocks = iStat.availableBlocksLong
//            val iTotalBlocks = iStat.blockCountLong
//            val iAvailableSpace = formatSize(iAvailableBlocks * iBlockSize)
//            val iTotalSpace = formatSize(iTotalBlocks * iBlockSize)

            return deviceInfo
        }

        fun getWordAfterKeyword(fullString: String, keyword: String): String? {
            // Escape the keyword to avoid regex special characters issues
            val escapedKeyword = Regex.escape(keyword)
            // Regex to find the keyword followed by a word
            val regex = Regex("$escapedKeyword\\s+(\\w+)")
            val matchResult = regex.find(fullString)
            return matchResult?.groups?.get(1)?.value
        }

        fun fetchPanCardNumber(fullString: String): String? {
            val panCardPattern = "[A-Z]{5}[0-9]{4}[A-Z]"
            val regex = Regex(panCardPattern)
            val matchResult = regex.find(fullString)
            return matchResult?.value
        }

        fun getBitmapFromUri(context: Context, uri: Uri): Bitmap? {
            return try {
                val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
                BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        private fun formatSize(size: Long): String? {
            var size = size
            var suffix: String? = null
            if (size >= 1024) {
                suffix = " KB"
                size /= 1024
                if (size >= 1024) {
                    suffix = " MB"
                    size /= 1024
                    if(size >= 1024){
                        suffix = " GB"
                        size /= 1024
                    }
                }
            }
            val resultBuffer = StringBuilder(java.lang.Long.toString(size))
            var commaOffset = resultBuffer.length - 3
            while (commaOffset > 0) {
                resultBuffer.insert(commaOffset, ',')
                commaOffset -= 3
            }
            if (suffix != null) resultBuffer.append(suffix)
            return resultBuffer.toString()
        }




    }

}