package com.los3molineros.logisticstycoon.view

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
import com.los3molineros.logisticstycoon.databinding.ActivityMainBinding
import com.los3molineros.logisticstycoon.viewModel.MainViewModel
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mainViewModel.version.observe(this, Observer { parameters ->
            if (parameters!=null) {
                binding.txtVersion.text = parameters.versionName

                if (parameters.versionCode!= BuildConfig.VERSION_CODE) {
                    showVersionDialog()
                }
            }
        })

        mainViewModel.quote.observe(this, Observer {
            if (it!=null) {
                val strings: Int = resources.getIdentifier(it.quote, "string", packageName)
                binding.txtQuote.text = getString(strings)
            }
        })

        mainViewModel.userExists.observe(this, Observer { userExists ->
            CoroutineScope(Dispatchers.IO).launch {
                delay(TimeUnit.SECONDS.toMillis(3))
                withContext(Dispatchers.Main) {
                    if (userExists) {
                        startActivity(Intent(this@MainActivity, MainActivity::class.java))
                    } else {
                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    }
                }
            }

        })

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
    try{
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
    } catch (e: ActivityNotFoundException) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
    }
}
