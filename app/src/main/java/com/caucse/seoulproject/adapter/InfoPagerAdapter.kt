package com.caucse.seoulproject.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log
import android.view.ViewGroup
import com.caucse.seoulproject.fragment.InfoFragmentInfo
import com.caucse.seoulproject.fragment.InfoFragmentMap
import com.caucse.seoulproject.fragment.InfoFragmentReview

class InfoPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm){
    private val TAG = this::class.java.simpleName

    init {
        Log.d(TAG, "init()")
    }

    override fun getItem(position: Int): Fragment? {
        Log.d(TAG, "$position")
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

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "문화 정보"
            1 -> "위치 정보"
            2 -> "리뷰"
            else -> null
        }
    }
}