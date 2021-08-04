package com.los3molineros.logisticstycoon.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion


class TrailerFragment : Fragment() {
    lateinit var mTxtTrailer2: TextView
    lateinit var mTxtTrailer3: TextView
    lateinit var mTxtTrailer4: TextView
    lateinit var mTxtTrailer5: TextView
    lateinit var mTxtTrailer6: TextView
    lateinit var mTxtTrailer7: TextView
    lateinit var mTxtTrailer8: TextView
    lateinit var mTxtTrailer9: TextView
    lateinit var mTxtTrailer10: TextView
    
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_trailer, container, false)

        mTxtTrailer2 = view.findViewById(R.id.txtTrailer2)
        mTxtTrailer3 = view.findViewById(R.id.txtTrailer3)
        mTxtTrailer4 = view.findViewById(R.id.txtTrailer4)
        mTxtTrailer5 = view.findViewById(R.id.txtTrailer5)
        mTxtTrailer6 = view.findViewById(R.id.txtTrailer6)
        mTxtTrailer7 = view.findViewById(R.id.txtTrailer7)
        mTxtTrailer8 = view.findViewById(R.id.txtTrailer8)
        mTxtTrailer9 = view.findViewById(R.id.txtTrailer9)
        mTxtTrailer10 = view.findViewById(R.id.txtTrailer10)


        mTxtTrailer2.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtTrailer3.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtTrailer4.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtTrailer5.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtTrailer6.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtTrailer7.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtTrailer8.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtTrailer9.typeface = Companion.returnTypefaceKingthings(view.context)
        mTxtTrailer10.typeface = Companion.returnTypefaceKingthings(view.context)
        
        
        return view
    }
    
}