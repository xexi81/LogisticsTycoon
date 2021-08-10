package com.los3molineros.logisticstycoon.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.los3molineros.logisticstycoon.BuildConfig
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion
import com.los3molineros.logisticstycoon.databinding.ActivityMenuBinding
import com.los3molineros.logisticstycoon.view.headquarterMenu.HeadquarterActivity
import com.los3molineros.logisticstycoon.view.partnershipMenu.PartnershipActivity
import com.los3molineros.logisticstycoon.view.trucksMenu.TruckMenuActivity
import com.los3molineros.logisticstycoon.view.userMenu.UserMenuActivity
import com.los3molineros.logisticstycoon.viewModel.MenuViewModel
import com.squareup.picasso.Picasso

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
            if (userExists) {
                menuViewModel.insertOrUpdateUser()
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }


        // load Images from Storage cloud Firebase
        menuViewModel.returnUserMenuImage(getString(R.string.userMenuImage))

        menuViewModel.userMenuImage.observe(this) { tittle ->
            Picasso.get().load(tittle).into(binding.ivUser)
        }

        menuViewModel.returnTrucksMenuImage(getString(R.string.trucksMenuImage))

        menuViewModel.trucksMenuImage.observe(this) { tittle ->
            Picasso.get().load(tittle).into(binding.ivTrucks)
        }

        menuViewModel.returnRoutesMenuImage(getString(R.string.routesMenuImage))

        menuViewModel.routesMenuImage.observe(this) { tittle ->
            Picasso.get().load(tittle).into(binding.ivRoutes)
        }

        menuViewModel.returnHeadquarterMenuImage(getString(R.string.headquarterMenuImage))

        menuViewModel.headquarterMenuImage.observe(this) { tittle ->
            Picasso.get().load(tittle).into(binding.ivHeadquarter)
        }

        menuViewModel.returnPartnershipMenuImage(getString(R.string.partnershipMenuImage))

        menuViewModel.partnershipMenuImage.observe(this) { tittle ->
            Picasso.get().load(tittle).into(binding.ivPartnership)
        }

        menuViewModel.returnStoreMenuImage(getString(R.string.storeMenuImage))

        menuViewModel.storeMenuImage.observe(this) { tittle ->
            Picasso.get().load(tittle).into(binding.ivStore)
        }



        // ON CLICK LISTENERS
        // user Posit
        menuViewModel.user.observe(this) {
            if (it?.nickname == null) {
                binding.layoutUserMenu.setOnClickListener {
                    startActivity(Intent(this, FirstFieldsActivity::class.java))
                }

                binding.layoutTruckMenu.setOnClickListener {
                    startActivity(Intent(this, FirstFieldsActivity::class.java))
                }

                binding.layoutHeadquarterMenu.setOnClickListener {
                    startActivity(Intent(this, FirstFieldsActivity::class.java))
                }

                binding.layoutPartnership.setOnClickListener {
                    startActivity(Intent(this, FirstFieldsActivity::class.java))
                }
            } else {
                binding.layoutUserMenu.setOnClickListener {
                    startActivity(Intent(this, UserMenuActivity::class.java))
                }

                binding.layoutTruckMenu.setOnClickListener {
                    startActivity(Intent(this, TruckMenuActivity::class.java))
                }

                binding.layoutHeadquarterMenu.setOnClickListener {
                    startActivity(Intent(this, HeadquarterActivity::class.java))
                }

                binding.layoutPartnership.setOnClickListener {
                    startActivity(Intent(this, PartnershipActivity::class.java))
                }
            }
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
