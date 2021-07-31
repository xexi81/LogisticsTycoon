package com.los3molineros.logisticstycoon.common

import android.app.Activity
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText

fun TextView.checkEmpty(): Boolean {
    return TextUtils.isEmpty(this.text)
}

fun Activity.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    makeText(this, message, duration).show()
}