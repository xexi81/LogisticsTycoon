package com.los3molineros.logisticstycoon.common

import android.content.Context
import android.graphics.Typeface
import java.lang.String.format
import java.text.NumberFormat
import java.util.*


class Companion {

    companion object {
        fun returnTypefaceKimbalt(context: Context): Typeface {
            return  Typeface.createFromAsset(context.assets, "fonts/kimbalt_.ttf")
        }

        fun returnTypefaceKingthings(context: Context): Typeface {
            return Typeface.createFromAsset(context.assets, "fonts/Kingthings Serifique.ttf")
        }

        fun returnCurrencyFormat(number: Int): String {
            return NumberFormat.getInstance().format(number)
        }
    }
}