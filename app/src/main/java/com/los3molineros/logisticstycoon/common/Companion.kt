package com.los3molineros.logisticstycoon.common

import android.content.Context
import android.graphics.Typeface


class Companion {

    companion object {
        fun returnTypefaceKimbalt(context: Context): Typeface {
            return  Typeface.createFromAsset(context.assets, "fonts/kimbalt_.ttf")
        }

        fun returnTypefaceKingthings(context: Context): Typeface {
            return Typeface.createFromAsset(context.assets, "fonts/Kingthings Serifique.ttf")
        }
    }
}