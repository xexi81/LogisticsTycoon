package com.los3molineros.logisticstycoon.view.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion


class PartnershipFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_partnership_fragments, container, false)

        val mBtnSearchPartnership = view.findViewById<TextView>(R.id.btnSearchPartnership)

        mBtnSearchPartnership.typeface = Companion.returnTypefaceKingthings(view.context)
        mBtnSearchPartnership.setShadowLayer(5F, 0F, 0F, Color.BLACK)

        return view
    }


}