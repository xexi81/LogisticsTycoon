package com.los3molineros.logisticstycoon.view.partnershipMenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion
import com.los3molineros.logisticstycoon.databinding.ActivityPartnershipBinding
import com.los3molineros.logisticstycoon.view.fragments.PartnershipAchievementsFragment
import com.los3molineros.logisticstycoon.view.fragments.PartnershipFragment
import com.los3molineros.logisticstycoon.view.fragments.PartnershipMembersFragment

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

        // Members button
        binding.btnMembers.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PartnershipMembersFragment())
                .commitNow()

            binding.fabNewPartnership.visibility = View.GONE

            binding.btnPartnership.setBackgroundResource(R.drawable.radius_corner)
            binding.btnMembers.setBackgroundResource(R.drawable.radius_corner_selected)
            binding.btnChat.setBackgroundResource(R.drawable.radius_corner)
            binding.btnAchievements.setBackgroundResource(R.drawable.radius_corner)
        }

        // Partnership button
        binding.btnPartnership.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PartnershipFragment())
                .commitNow()

            binding.fabNewPartnership.visibility = View.VISIBLE

            binding.btnPartnership.setBackgroundResource(R.drawable.radius_corner_selected)
            binding.btnMembers.setBackgroundResource(R.drawable.radius_corner)
            binding.btnChat.setBackgroundResource(R.drawable.radius_corner)
            binding.btnAchievements.setBackgroundResource(R.drawable.radius_corner)
        }

        // Achievement button
        binding.btnAchievements.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PartnershipAchievementsFragment())
                .commitNow()

            binding.fabNewPartnership.visibility = View.GONE

            binding.btnPartnership.setBackgroundResource(R.drawable.radius_corner)
            binding.btnMembers.setBackgroundResource(R.drawable.radius_corner)
            binding.btnChat.setBackgroundResource(R.drawable.radius_corner)
            binding.btnAchievements.setBackgroundResource(R.drawable.radius_corner_selected)
        }

    }
}