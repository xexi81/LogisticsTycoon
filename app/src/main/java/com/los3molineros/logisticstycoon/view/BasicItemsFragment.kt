package com.los3molineros.logisticstycoon.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.los3molineros.logisticstycoon.R
import com.los3molineros.logisticstycoon.common.Companion.Companion.returnTypeface


class BasicItemsFragment : Fragment() {
    lateinit var mTxtBread: TextView
    lateinit var mTxtGasoil: TextView
    lateinit var mTxtUtils: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_basic_items, container, false)

        mTxtBread = view.findViewById(R.id.txtBread)
        mTxtGasoil = view.findViewById(R.id.txtGasoil)
        mTxtUtils = view.findViewById(R.id.txtUtils)


        mTxtBread.typeface = returnTypeface(view.context)
        mTxtGasoil.typeface = returnTypeface(view.context)
        mTxtUtils.typeface = returnTypeface(view.context)

        return view
    }

}