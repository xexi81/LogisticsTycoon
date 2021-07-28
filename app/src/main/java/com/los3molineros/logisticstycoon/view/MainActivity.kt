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
import androidx.lifecycle.lifecycleScope
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.databinding.ActivityMainBinding
import com.los3molineros.logisticstycoon.viewModel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        mainViewModel.version.observe(this, Observer {
            if (it!=null) {
                binding.txtVersion.text = it.versionName
                lifecycleScope.launch { mainViewModel.searchSharedVersion(baseContext, it.versionCode) }
            }
        })


        mainViewModel.quote.observe(this, Observer {
            if (it!=null) {
                val strings: Int = resources.getIdentifier(it.quote, "string", packageName)
                binding.txtQuote.text = getString(strings)
            }
        })

        mainViewModel.isActualVersion.observe(this, Observer {
            if (!it) {
                showVersionDialog()
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
