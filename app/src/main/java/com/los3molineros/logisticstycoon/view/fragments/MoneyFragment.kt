package com.los3molineros.logisticstycoon.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion.Companion.returnCurrencyFormat
import com.los3molineros.logisticstycoon.common.Companion.Companion.returnTypefaceKingthings
import com.los3molineros.logisticstycoon.viewModel.MoneyViewModel


class MoneyFragment : Fragment() {
    lateinit var mTxtMoney: TextView
    lateinit var mTxtGem: TextView
    private val moneyViewModel: MoneyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_money, container, false)

        mTxtMoney = view.findViewById(R.id.txtMoney)
        mTxtGem = view.findViewById(R.id.txtGem)


        mTxtMoney.typeface = returnTypefaceKingthings(view.context)
        mTxtGem.typeface = returnTypefaceKingthings(view.context)

        moneyViewModel.money.observe(viewLifecycleOwner) {
            mTxtMoney.text = returnCurrencyFormat(it)
        }

        moneyViewModel.gems.observe(requireActivity()) {
            mTxtGem.text = returnCurrencyFormat(it)
        }


        return view
    }


}