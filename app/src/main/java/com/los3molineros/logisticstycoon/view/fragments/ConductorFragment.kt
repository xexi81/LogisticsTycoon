package com.los3molineros.logisticstycoon.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion

class ConductorFragment : Fragment() {
    lateinit var mTxtConductor2: TextView
    lateinit var mTxtConductor3: TextView
    lateinit var mTxtConductor4: TextView
    lateinit var mTxtConductor5: TextView
    lateinit var mTxtConductor6: TextView
    lateinit var mTxtConductor7: TextView
    lateinit var mTxtConductor8: TextView
    lateinit var mTxtConductor9: TextView
    lateinit var mTxtConductor10: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_conductor, container, false)

        mTxtConductor2 = view.findViewById(R.id.txtConductor2)
        mTxtConductor3 = view.findViewById(R.id.txtConductor3)
        mTxtConductor4 = view.findViewById(R.id.txtConductor4)
        mTxtConductor5 = view.findViewById(R.id.txtConductor5)
        mTxtConductor6 = view.findViewById(R.id.txtConductor6)
        mTxtConductor7 = view.findViewById(R.id.txtConductor7)
        mTxtConductor8 = view.findViewById(R.id.txtConductor8)
        mTxtConductor9 = view.findViewById(R.id.txtConductor9)
        mTxtConductor10 = view.findViewById(R.id.txtConductor10)


        mTxtConductor2.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtConductor3.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtConductor4.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtConductor5.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtConductor6.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtConductor7.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtConductor8.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtConductor9.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtConductor10.typeface = Companion.returnTypefaceKingthings(view.context)

        return view
    }

}