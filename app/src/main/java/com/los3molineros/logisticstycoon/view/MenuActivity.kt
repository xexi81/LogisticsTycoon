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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.los3molineros.logisticstycoon.BuildConfig
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.databinding.ActivityMenuBinding
import com.los3molineros.logisticstycoon.viewModel.MainViewModel
import com.los3molineros.logisticstycoon.viewModel.MenuViewModel

class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding
    private val menuViewModel: MenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

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



        // User UUID
        val currentUser = FirebaseAuth.getInstance().currentUser

        binding.txtUid.text = currentUser?.uid ?: " "

        // Sign out
        binding.btnExit.setOnClickListener {
            val mAuth = FirebaseAuth.getInstance()

            val googleSignInOptions = GoogleSignInOptions.Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

            mAuth.signOut()
            mGoogleSignInClient.signOut()


            // finish all the previous activities
            val intent = Intent(this,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
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