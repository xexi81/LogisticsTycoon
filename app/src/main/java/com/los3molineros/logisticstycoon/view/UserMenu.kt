package com.los3molineros.logisticstycoon.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion
import com.los3molineros.logisticstycoon.databinding.ActivityUserMenuBinding



class UserMenu : AppCompatActivity() {
    lateinit var binding: ActivityUserMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUserMenuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fBasicItems, BasicItemsFragment())
                .commitNow()
        }

        binding.idTittle.typeface = Companion.returnTypeface(this)
        binding.txtUsername.typeface = Companion.returnTypeface(this)
        binding.btnUsername.typeface = Companion.returnTypeface(this)
        binding.btnUsername.setShadowLayer(2F, 0F, 0F, Color.BLACK)



        // set on clicks
        binding.ivExit.setOnClickListener {
            finish()
        }
    }
}