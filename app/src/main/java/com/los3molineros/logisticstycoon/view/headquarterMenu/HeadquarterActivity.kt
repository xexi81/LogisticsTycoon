package com.los3molineros.logisticstycoon.view.headquarterMenu

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion
import com.los3molineros.logisticstycoon.databinding.ActivityHeadquarterBinding
import com.los3molineros.logisticstycoon.view.fragments.MoneyFragment

class HeadquarterActivity : AppCompatActivity() {
    lateinit var binding: ActivityHeadquarterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHeadquarterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.moneyFragment, MoneyFragment())
                .commitNow()
        }


        binding.txtTittle.typeface = Companion.returnTypefaceKimbalt(this)
        binding.txtBasicWarehouse.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtBasicWarehouse.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtBasicWarehouse.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtPrimayWarehouse.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtSecondaryWarehouse.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtFinalWarehouse.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtUpgradeGems.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtUpgradeMoney.typeface = Companion.returnTypefaceKingthings(this)



        // Progress Bar
        binding.txtLevel.text = "NIVEL 27"
        binding.txtLevel.typeface = Companion.returnTypefaceKingthings(this)
        binding.pbLevel.max = 500
        binding.pbLevel.progress = 25

        // set on clicks
        binding.ivExit.setOnClickListener {
            finish()
        }
    }
}