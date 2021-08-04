package com.los3molineros.logisticstycoon.view

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.los3molineros.logisticstycoon.BuildConfig
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion
import com.los3molineros.logisticstycoon.databinding.ActivityMenuBinding
import com.los3molineros.logisticstycoon.viewModel.MenuViewModel

class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding
    private val menuViewModel: MenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtBaseMenu.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtGroupMenu.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtRoutes.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtTrucksMenu.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtUserMenu.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtStore.typeface = Companion.returnTypefaceKingthings(this)

        menuViewModel.parameters.observe(this, Observer { parameters ->
            if (parameters!=null && parameters.versionCode != BuildConfig.VERSION_CODE) {
                showVersionDialog()
            }
        })

        menuViewModel.userExists.observe(this) { userExists ->
            if (!userExists) {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        // ON CLICK LISTENERS
        // user Posit
        binding.layoutUserMenu.setOnClickListener {
            startActivity(Intent(this, UserMenuActivity::class.java))
        }

        binding.layoutTruckMenu.setOnClickListener {
            startActivity(Intent(this, TruckMenuActivity::class.java))
        }

        binding.layoutHeadquarterMenu.setOnClickListener {
            startActivity(Intent(this, HeadquarterActivity::class.java))
        }


    }

    fun showVersionDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder
            .setMessage(getString(R.string.actDescripcion))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.actSi)) { dialog, which ->
                openAppInPlaystore("com.mobile.legends", this)
            }
            .setNegativeButton(getString(R.string.actNo)) {dialog, which -> {
                dialog.dismiss()
            } }

        val alert = dialogBuilder.create()
        alert.setTitle(getString(R.string.actTitulo))
        alert.show()
    }
}


private fun openAppInPlaystore(appPackageName: String, context: Context) {
    try {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=$appPackageName")
            )
        )
    } catch (e: ActivityNotFoundException) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
            )
        )
    }
}