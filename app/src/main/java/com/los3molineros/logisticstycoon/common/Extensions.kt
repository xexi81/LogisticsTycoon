package com.los3molineros.logisticstycoon.common

import android.app.Activity
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import com.google.android.material.snackbar.Snackbar

fun TextView.checkEmpty(): Boolean {
    return TextUtils.isEmpty(this.text)
}

fun Activity.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    makeText(this, message, duration).show()
}

fun View.snakeBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}