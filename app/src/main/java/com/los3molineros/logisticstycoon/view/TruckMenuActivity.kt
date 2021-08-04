package com.los3molineros.logisticstycoon.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.databinding.ActivityTruckMenuBinding
import com.los3molineros.logisticstycoon.view.fragments.ConductorFragment
import com.los3molineros.logisticstycoon.view.fragments.MoneyFragment
import com.los3molineros.logisticstycoon.view.fragments.TrailerFragment
import com.los3molineros.logisticstycoon.view.fragments.TruckFragment

class TruckMenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityTruckMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTruckMenuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.moneyFragment, MoneyFragment())
                .commitNow()

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TruckFragment())
                .commitNow()
        }

        binding.txtTrucksOption.setBackgroundResource(R.drawable.radius_corner_selected)





        // set on clicks
        binding.ivExit.setOnClickListener {
            finish()
        }

        binding.txtTrucksOption.setOnClickListener {
            binding.txtTrucksOption.setBackgroundResource(R.drawable.radius_corner_selected)
            binding.txtChofferOption.setBackgroundResource(R.drawable.radius_corner)
            binding.txtTrailersOption.setBackgroundResource(R.drawable.radius_corner)

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TruckFragment())
                .commitNow()
        }

        binding.txtChofferOption.setOnClickListener {
            binding.txtTrucksOption.setBackgroundResource(R.drawable.radius_corner)
            binding.txtChofferOption.setBackgroundResource(R.drawable.radius_corner_selected)
            binding.txtTrailersOption.setBackgroundResource(R.drawable.radius_corner)

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ConductorFragment())
                .commitNow()
        }

        binding.txtTrailersOption.setOnClickListener {
            binding.txtTrucksOption.setBackgroundResource(R.drawable.radius_corner)
            binding.txtChofferOption.setBackgroundResource(R.drawable.radius_corner)
            binding.txtTrailersOption.setBackgroundResource(R.drawable.radius_corner_selected)

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TrailerFragment())
                .commitNow()
        }

    }
}