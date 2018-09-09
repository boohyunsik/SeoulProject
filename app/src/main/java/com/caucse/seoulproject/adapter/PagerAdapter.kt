package com.caucse.seoulproject.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.caucse.seoulproject.fragment.InfoFragmentInfo
import com.caucse.seoulproject.fragment.InfoFragmentMap
import com.caucse.seoulproject.fragment.InfoFragmentReview

class PagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment? {
        return when (position){
            0 -> InfoFragmentInfo()
            1 -> InfoFragmentMap()
            2 -> InfoFragmentReview()
            else  -> null
        }
    }

    override fun getCount(): Int {
       return 3
    }
}