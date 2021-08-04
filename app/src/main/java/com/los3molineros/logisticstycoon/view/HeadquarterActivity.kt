package com.los3molineros.logisticstycoon.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion
import com.los3molineros.logisticstycoon.databinding.ActivityHeadquarterBinding

class HeadquarterActivity : AppCompatActivity() {
    lateinit var binding: ActivityHeadquarterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHeadquarterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.txtTittle.typeface = Companion.returnTypefaceKimbalt(this)
        binding.txtBasicWarehouse.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtBasicWarehouse.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtBasicWarehouse.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtPrimayWarehouse.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtSecondaryWarehouse.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtFinalWarehouse.typeface = Companion.returnTypefaceKingthings(this)



        // set on clicks
        binding.ivExit.setOnClickListener {
            finish()
        }
    }
}