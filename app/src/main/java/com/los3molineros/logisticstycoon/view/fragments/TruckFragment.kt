package com.los3molineros.logisticstycoon.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion


class TruckFragment : Fragment() {
    lateinit var mTxtTruck2: TextView
    lateinit var mTxtTruck3: TextView
    lateinit var mTxtTruck4: TextView
    lateinit var mTxtTruck5: TextView
    lateinit var mTxtTruck6: TextView
    lateinit var mTxtTruck7: TextView
    lateinit var mTxtTruck8: TextView
    lateinit var mTxtTruck9: TextView
    lateinit var mTxtTruck10: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_truck, container, false)

        mTxtTruck2 = view.findViewById(R.id.txtTruck2)
        mTxtTruck3 = view.findViewById(R.id.txtTruck3)
        mTxtTruck4 = view.findViewById(R.id.txtTruck4)
        mTxtTruck5 = view.findViewById(R.id.txtTruck5)
        mTxtTruck6 = view.findViewById(R.id.txtTruck6)
        mTxtTruck7 = view.findViewById(R.id.txtTruck7)
        mTxtTruck8 = view.findViewById(R.id.txtTruck8)
        mTxtTruck9 = view.findViewById(R.id.txtTruck9)
        mTxtTruck10 = view.findViewById(R.id.txtTruck10)


        mTxtTruck2.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtTruck3.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtTruck4.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtTruck5.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtTruck6.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtTruck7.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtTruck8.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtTruck9.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtTruck10.typeface = Companion.returnTypefaceKingthings(view.context)

        return view
    }

}