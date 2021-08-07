package com.los3molineros.logisticstycoon.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion


class MoneyPartnershipFragment : Fragment() {
    lateinit var mTxtMoney: TextView
    lateinit var mTxtGem: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_money_partnership, container, false)

        mTxtMoney = view.findViewById(R.id.txtMoney)


        mTxtMoney.typeface = Companion.returnTypefaceKingthings(view.context)

        return view
    }

}