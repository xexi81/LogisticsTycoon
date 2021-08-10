package com.los3molineros.logisticstycoon.view

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion
import com.los3molineros.logisticstycoon.common.toast
import com.los3molineros.logisticstycoon.databinding.ActivityFirstFieldsBinding
import com.los3molineros.logisticstycoon.model.repository.CitiesRepository
import com.los3molineros.logisticstycoon.viewModel.FirstFieldsViewModel
import com.squareup.picasso.Picasso

class FirstFieldsActivity : AppCompatActivity() {
    lateinit var binding: ActivityFirstFieldsBinding
    private val firstFieldsViewModel: FirstFieldsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstFieldsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Styles
        binding.txtTittle.typeface = Companion.returnTypefaceKimbalt(this)
        binding.txtNickname.typeface = Companion.returnTypefaceKingthings(this)
        binding.txtBase.typeface = Companion.returnTypefaceKingthings(this)
        binding.btnFirstFields.typeface = Companion.returnTypefaceKingthings(this)
        binding.btnFirstFields.setShadowLayer(10F, 0F, 0F, Color.BLACK)
        binding.etNickname.typeface = Companion.returnTypefaceKingthings(this)

        // Images
        firstFieldsViewModel.returnNicknameImage()

        firstFieldsViewModel.nicknameImage.observe(this) { image ->
            Picasso.get().load(image).into(binding.ivNickname)
        }

        firstFieldsViewModel.returnBaseImage()

        firstFieldsViewModel.baseImage.observe(this) { image ->
            Picasso.get().load(image).into(binding.ivBase)
        }

        // Spinner
        val citiesList = CitiesRepository()
        val listOfCities = citiesList.returnCityNameList()

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfCities)

        binding.spBase.adapter = adapter
        binding.spBase.setSelection(-1, false)


        // Click on Button
        binding.btnFirstFields.setOnClickListener {
            if (binding.etNickname.text.isNullOrEmpty()) {
                toast(getString(R.string.nickname_error))
            }

            else if (binding.spBase.selectedItem.toString().isEmpty()) {
                toast(getString(R.string.base_error))
            }

            else {
                firstFieldsViewModel.assignNicknameAndBase(binding.etNickname.text.toString(), binding.spBase.selectedItem.toString())
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }
}