package com.los3molineros.logisticstycoon.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.los3molineros.logisticstycoon.BuildConfig
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion
import com.los3molineros.logisticstycoon.databinding.ActivityMainBinding
import com.los3molineros.logisticstycoon.viewModel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()


        // Text Style
        val text = "${getString(R.string.version)} ${BuildConfig.VERSION_NAME} "
        binding.txtVersion.text = text
        binding.txtVersion.typeface = Companion.returnTypefaceKingthings(this)

    }

    private fun initUI() {
        initSubscription()
    }

    private fun initSubscription() {
        // Quotes
        mainViewModel.quote.observe(this, {
            if (it!=null) {
                val strings: Int = resources.getIdentifier(it.quote, "string", packageName)
                binding.txtQuote.text = getString(strings)
                binding.txtQuote.typeface = Companion.returnTypefaceKingthings(this)
            }
        })


        // Delay of 3 secs and navigation to Menu or Login
        mainViewModel.userExists.observe(this, { userExists ->
            CoroutineScope(Dispatchers.IO).launch {
                delay(TimeUnit.SECONDS.toMillis(3))
                withContext(Dispatchers.Main) {
                    if (userExists) {
                        startActivity(Intent(this@MainActivity, MenuActivity::class.java))
                    } else {
                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    }
                }
            }

        })
    }

}
