

package com.example.tasklist.base.extensions

import android.R
import android.content.Context
import android.graphics.Bitmap
import android.transition.Transition
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget


fun String.isNameOrUserNameValid(
    onError : ()->Unit
): Boolean {
    if (this == "") {
        onError()
        return false
    }
    return true
}

fun Context.getImage(url:String, onBitmap: (Bitmap)->Unit){
    Glide.with(this)
        .asBitmap()
        .load(url)
        .into(object : SimpleTarget<Bitmap?>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: com.bumptech.glide.request.transition.Transition<in Bitmap?>?
            ) {
                onBitmap(resource)
            }

        })
}


fun Context.showToast(message: String){
    Toast.makeText(this,message, Toast.LENGTH_LONG).show()
}

fun String.isEmailValid(onError: () -> Unit): Boolean {
    if (this.isEmpty()) {
        onError()
        return false
    }

    val parts = this.split('@')

    if (parts.size != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
        onError()
        return false
    }

    val domainParts = parts[1].split('.')

    if (domainParts.size < 2 || domainParts.any { it.isEmpty() }) {
        onError()
        return false
    }

    return true
}

fun String.isPasswordKonfirmValid(
    confirm : String,onError : ()->Unit
): Boolean {
    if (this != confirm || this.isEmpty()) {
        onError()
        return false
    }
    return true
}

fun String.isOtpValid(
    onError: () -> Unit
): Boolean {
    if(this.length != 6 && this.toIntOrNull() != null){
        onError()
        return false
    }
    return true
}

fun String.isPasswordValid(
    onError : ()->Unit
): Boolean {
    if (this.length < 6) {
        onError()
        return false
    }
    return true
}

fun String.isNomorTeleponValid(
    onError: () -> Unit
): Boolean {
    if (this.length < 7 && this.toDoubleOrNull() != null) {
        onError()
        return false
    }
    return true
}





