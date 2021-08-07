package com.los3molineros.logisticstycoon.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.type.Money
import com.los3molineros.logisticstycoon.R


class PartnershipAchievementsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_partnership_achievements, container, false)

        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction().replace(R.id.moneyPartnershipFragment, MoneyPartnershipFragment()).commitNow()
        }



        return view
    }

}