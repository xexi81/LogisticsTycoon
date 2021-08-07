package com.los3molineros.logisticstycoon.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion
import com.los3molineros.logisticstycoon.databinding.ActivityPartnershipBinding
import com.los3molineros.logisticstycoon.view.fragments.PartnershipFragment

class PartnershipActivity : AppCompatActivity() {
    lateinit var binding: ActivityPartnershipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPartnershipBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.idTittle.typeface = Companion.returnTypefaceKimbalt(this)
        binding.btnAchievements.typeface = Companion.returnTypefaceKingthings(this)
        binding.btnChat.typeface = Companion.returnTypefaceKingthings(this)
        binding.btnMembers.typeface = Companion.returnTypefaceKingthings(this)
        binding.btnPartnership.typeface = Companion.returnTypefaceKingthings(this)

        binding.btnPartnership.setBackgroundResource(R.drawable.radius_corner_selected)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PartnershipFragment())
                .commitNow()
        }


        // On Clicks
        binding.ivExit.setOnClickListener {
            finish()
        }

    }
}