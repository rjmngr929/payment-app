package com.example.paymentapplication.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.paymentapplication.R
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar


fun View.visible() {
    visibility = View.VISIBLE
}

//componentId.visible()
fun View.gone() {
    visibility = View.GONE
}
//componentId.invisible()
fun View.invisible() {
    visibility = View.INVISIBLE
}

//rootView.showSnack(state.message, getString(R.string.action_retry_str)) {
//    viewModel.retry()
//}
fun View.showSnack(message: String, action: String = "", actionListener: () -> Unit = {}): Snackbar {
    var snackbar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    if (action != "") {
        snackbar.duration = Snackbar.LENGTH_INDEFINITE
        snackbar.setAction(action) {
            actionListener()
            snackbar.dismiss()
        }
    }
    snackbar.show()
    return snackbar
}

fun String.capitalizeFirstLetter(): String {
    return if (isNotEmpty()) {
        this[0].uppercaseChar() + substring(1)
    } else {
        this
    }
}

fun String.isValidEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isAlphabetWithSpace(): Boolean {
    return this.matches(Regex("^[a-zA-Z\\s]+$"))
}

fun String.isValidPan(): Boolean {
    return this.matches(Regex("^[A-Za-z]{5}[0-9]{4}[A-Za-z]$"))
}

fun String.isValidAadhaar(): Boolean {
    return this.matches(Regex("^[2-9]{1}[0-9]{11}$"))
}

//Animations
fun View.toptobottomAnimation(myContext: Context) {
   return startAnimation(AnimationUtils.loadAnimation(myContext , R.anim.ttb))
}

fun View.zoomOutAnimation(myContext: Context) {
    return startAnimation(AnimationUtils.loadAnimation(myContext , R.anim.stb))
}

fun View.bottomtotop_type_oneAnimation(myContext: Context) {
    return startAnimation(AnimationUtils.loadAnimation(myContext , R.anim.btt))
}

fun View.bottomtotop_type_twoAnimation(myContext: Context) {
    return startAnimation(AnimationUtils.loadAnimation(myContext , R.anim.btt2))
}

fun logInfo(tag: String, msg: String): Int{
    return Log.d(tag, msg)
}

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

//showToast(getString(R.string.message_load_photos_str))
fun Fragment.showToast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

//showToast(getString(R.string.message_load_photos_str))
fun Activity.showToast(myContext: Context,message: String) {
    Toast.makeText(myContext, message, Toast.LENGTH_SHORT).show()
}

//Loader
fun getLoadingDialog(myContext: Context): AlertDialog {
    val alertDialogBuilder = AlertDialog.Builder(myContext, R.style.MyDialogStyle_transparent)
    alertDialogBuilder.setView(R.layout.layout_loading_alert_dialog)
    alertDialogBuilder.setCancelable(false)

    return alertDialogBuilder.create()
}

//show Loader
fun showLoader(myContext: Context, loader: AlertDialog){
    loader.show()
}

//hide loader
fun hideLoader(myContext: Context, loader: AlertDialog){
    if (loader.isShowing)
        loader.dismiss()
}

fun EditText.dismissKeyboard() {
    val imm: InputMethodManager? = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.hideSoftInputFromWindow(windowToken, 0)
}

fun EditText.showKeyboard() {
    val imm: InputMethodManager? = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

/**
 * Returns [Boolean] based on current time.
 * Returns true if hours are between 06:00 pm - 07:00 am
 */
fun isNight(): Boolean {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return (currentHour <= 7 || currentHour >= 18)
}