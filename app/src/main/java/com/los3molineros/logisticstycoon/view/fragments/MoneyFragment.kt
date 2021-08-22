package com.los3molineros.logisticstycoon.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion.Companion.returnCurrencyFormat
import com.los3molineros.logisticstycoon.common.Companion.Companion.returnTypefaceKingthings
import com.los3molineros.logisticstycoon.viewModel.MoneyViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.ExperimentalCoroutinesApi


class MoneyFragment : Fragment() {
    lateinit var mTxtMoney: TextView
    lateinit var mTxtGem: TextView
    lateinit var mIvMoney: ImageView
    lateinit var mIvGems: ImageView

    @ExperimentalCoroutinesApi
    private val moneyViewModel: MoneyViewModel by viewModels()

    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_money, container, false)

        // Declare
        mTxtMoney = view.findViewById(R.id.txtMoney)
        mTxtGem = view.findViewById(R.id.txtGem)
        mIvMoney = view.findViewById(R.id.ivMoney)
        mIvGems = view.findViewById(R.id.ivGems)

        // Images from storage
        moneyViewModel.returnMoneyImage("gs://logistics-tycoon.appspot.com/money_icon.png")
        moneyViewModel.returnGemsImage("gs://logistics-tycoon.appspot.com/gem_icon.png")

        // Styles
        mTxtMoney.typeface = returnTypefaceKingthings(view.context)
        mTxtGem.typeface = returnTypefaceKingthings(view.context)

        // ViewModel to obtain money and format it
        moneyViewModel.money.observe(viewLifecycleOwner) {
            mTxtMoney.text = returnCurrencyFormat(it)
        }

        // ViewModel to obtain gems and format it
        moneyViewModel.gems.observe(viewLifecycleOwner) {
            mTxtGem.text = returnCurrencyFormat(it)
        }


        // ViewModel to format images from storage
        moneyViewModel.moneyImage.observe(viewLifecycleOwner) {
            Picasso.get().load(it).into(mIvMoney)
        }

        moneyViewModel.gemsImage.observe(viewLifecycleOwner) {
            Picasso.get().load(it).into(mIvGems)
        }



        return view
    }


}