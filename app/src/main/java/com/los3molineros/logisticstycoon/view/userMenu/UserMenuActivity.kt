package com.los3molineros.logisticstycoon.view.userMenu

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion
import com.los3molineros.logisticstycoon.databinding.ActivityUserMenuBinding
import com.los3molineros.logisticstycoon.model.signOutFirebase
import com.los3molineros.logisticstycoon.view.MainActivity
import com.los3molineros.logisticstycoon.view.alerts.NicknameDialog
import com.los3molineros.logisticstycoon.view.fragments.MoneyFragment
import com.los3molineros.logisticstycoon.viewModel.UserMenuViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi


class UserMenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserMenuBinding

    @ExperimentalCoroutinesApi
    private val userMenuViewModel: UserMenuViewModel by viewModels()

    @ExperimentalCoroutinesApi
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUserMenuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fBasicItems, MoneyFragment())
                .commitNow()
        }

        binding.idTittle.typeface = Companion.returnTypefaceKimbalt(this)
        binding.txtUsername.typeface = Companion.returnTypefaceKingthings(this)
        binding.btnUsername.typeface = Companion.returnTypefaceKingthings(this)
        binding.btnUsername.setShadowLayer(5F, 0F, 0F, Color.BLACK)
        binding.btnAvatar.typeface = Companion.returnTypefaceKingthings(this)
        binding.btnAvatar.setShadowLayer(5F, 0F, 0F, Color.BLACK)
        binding.txtNextLevel.text = "579 ${getString(R.string.level2)}"
        binding.txtNextLevel.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtLevel.text = "NIVEL 275"
        binding.txtLevel.typeface = Companion.returnTypefaceKingthings(this)

        // Progress Bar
        binding.pbLevel.max = 100
        binding.pbLevel.progress = 25
        binding.txtProgressLevel.text = "25%"
        binding.txtProgressLevel.setShadowLayer(10F, 0F, 0F, Color.BLACK)
        binding.txtProgressLevel.typeface = Companion.returnTypefaceKingthings(this)

        // Options
        binding.notificationOff.typeface = Companion.returnTypefaceKingthings(this)
        binding.notificationOff.typeface = Companion.returnTypefaceKingthings(this)
        binding.soundOff.typeface = Companion.returnTypefaceKingthings(this)
        binding.soundOn.typeface = Companion.returnTypefaceKingthings(this)
        binding.musicOff.typeface = Companion.returnTypefaceKingthings(this)
        binding.musicOn.typeface = Companion.returnTypefaceKingthings(this)
        binding.btnOptions.typeface = Companion.returnTypefaceKingthings(this)
        binding.btnOptions.setShadowLayer(5F, 0F, 0F, Color.BLACK)
        binding.txtNotifications.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtMusic.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtSounds.typeface = Companion.returnTypefaceKingthings(this)

        // Player data
        binding.txtTrucksNumber.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtContractsNumber.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtMedalsNumber.typeface = Companion.returnTypefaceKingthings(this)
        binding.btnAdministrador.typeface = Companion.returnTypefaceKingthings(this)
        binding.btnAdministrador.setShadowLayer(5F, 0F, 0F, Color.BLACK)
        binding.btnSignOut.typeface = Companion.returnTypefaceKingthings(this)
        binding.btnSignOut.setShadowLayer(5F, 0F, 0F, Color.BLACK)
        binding.btnSearchPlayer.typeface = Companion.returnTypefaceKingthings(this)
        binding.btnSearchPlayer.setShadowLayer(5F, 0F, 0F, Color.BLACK)

        // Users data
        userMenuViewModel.user.observe(this) {
            binding.txtUsername.text = it?.nickname ?: ""
        }


        // set on clicks
        binding.ivExit.setOnClickListener {
            finish()
        }

        binding.btnUsername.setOnClickListener {
            NicknameDialog(this)
        }


        // Sign out Posit
        binding.btnSignOut.setOnClickListener {
            signOutFirebase(this)

            // finish all the previous activities
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}