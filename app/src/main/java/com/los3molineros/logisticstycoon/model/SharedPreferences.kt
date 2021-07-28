package com.los3molineros.logisticstycoon.model

import android.content.Context

fun searchSharedPreferencesVerion(context: Context, actualVersion: Int): Boolean {
    val prefs = context.getSharedPreferences("personalShared", 0)
    val version = prefs.getInt("version", 0)

    if (version==0 || version!=actualVersion) {
        prefs.edit().putInt("version", actualVersion).apply()
        return version == 0
    } else return true
}