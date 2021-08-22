package com.los3molineros.logisticstycoon.view

import android.app.Activity
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.los3molineros.logisticstycoon.BuildConfig
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion
import com.los3molineros.logisticstycoon.common.Companion.Companion.debugLog
import com.los3molineros.logisticstycoon.common.toast
import com.los3molineros.logisticstycoon.databinding.ActivityMenuBinding
import com.los3molineros.logisticstycoon.view.headquarterMenu.HeadquarterActivity
import com.los3molineros.logisticstycoon.view.partnershipMenu.PartnershipActivity
import com.los3molineros.logisticstycoon.view.trucksMenu.TruckMenuActivity
import com.los3molineros.logisticstycoon.view.userMenu.UserMenuActivity
import com.los3molineros.logisticstycoon.viewModel.MenuViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding

    @ExperimentalCoroutinesApi
    private val menuViewModel: MenuViewModel by viewModels()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

        binding.txtBaseMenu.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtGroupMenu.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtRoutes.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtTrucksMenu.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtUserMenu.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtStore.typeface = Companion.returnTypefaceKingthings(this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                1 -> startActivity(Intent(this, UserMenuActivity::class.java))
                2 -> startActivity(Intent(this, TruckMenuActivity::class.java))
                3 -> startActivity(Intent(this, HeadquarterActivity::class.java))
                4 -> startActivity(Intent(this, PartnershipActivity::class.java))
                5 -> toast("Option error")
            }
        }
    }

    override fun onBackPressed() {
        return
    }

    @ExperimentalCoroutinesApi
    private fun initUI() {
        debugLog(description = "entramos en initUI")
        initSubscription()
        initListeners()
    }

    @ExperimentalCoroutinesApi
    private fun initListeners() {
        debugLog(description = "entramos en initListeners")
        menuViewModel.user.observe(this) {
            debugLog(description = "Entramos por el OBSERVER de initListeners")
            if (it?.nickname == null) {
                binding.layoutUserMenu.setOnClickListener {
                    startActivityForResult(Intent(this, FirstFieldsActivity::class.java), 1)
                }

                binding.layoutTruckMenu.setOnClickListener {
                    startActivityForResult(Intent(this, FirstFieldsActivity::class.java), 2)
                }

                binding.layoutHeadquarterMenu.setOnClickListener {
                    startActivityForResult(Intent(this, FirstFieldsActivity::class.java), 3)
                }

                binding.layoutPartnership.setOnClickListener {
                    startActivityForResult(Intent(this, FirstFieldsActivity::class.java), 4)
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

    @ExperimentalCoroutinesApi
    private fun initSubscription() {
        menuViewModel.parameters.observe(this, Observer { parameters ->
            if (parameters != null && parameters.versionCode != BuildConfig.VERSION_CODE) {
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
    }


    private fun showVersionDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder
            .setMessage(getString(R.string.actDescripcion))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.actSi)) { dialog, which ->
                openAppInPlaystore("com.mobile.legends", this)
            }
            .setNegativeButton(getString(R.string.actNo)) { dialog, which ->
                {
                    dialog.dismiss()
                }
            }

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
