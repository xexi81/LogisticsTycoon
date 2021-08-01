package com.los3molineros.logisticstycoon.common

import android.content.Context
import android.graphics.Typeface


class Companion {

    companion object {
        fun returnTypeface(context: Context): Typeface {
            return  Typeface.createFromAsset(context.assets, "fonts/kimbalt_.ttf")
        }
    }
}