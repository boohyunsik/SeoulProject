package com.caucse.seoulproject.fragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import com.caucse.seoulproject.R
import com.caucse.seoulproject.adapter.InfoPagerAdapter
import com.caucse.seoulproject.data.CultureRow
import com.caucse.seoulproject.helper.NSearchApiHelper
import com.caucse.seoulproject.utils.ImageUtil
import com.caucse.seoulproject.viewmodel.MainViewModel
import com.nhn.android.maps.NMapContext
import com.nhn.android.maps.NMapOverlay
import com.nhn.android.maps.NMapView
import com.nhn.android.maps.overlay.NMapPOIdata
import com.nhn.android.mapviewer.overlay.NMapOverlayManager
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay
import com.nhn.android.mapviewer.overlay.NMapResourceProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_info.view.*


class InfoFragment : NMapFragment() {

    val TAG = "InfoFragment"
    private lateinit var mMapView: NMapView
    private lateinit var mMapContext: NMapContext
    private lateinit var mainViewModel: MainViewModel
    private lateinit var pager : ViewPager
    private lateinit var adapter: InfoPagerAdapter
    private lateinit var tab : TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView()")
        var view: View = inflater.inflate(R.layout.fragment_info, container, false)
        tab = view.findViewById(R.id.infoTap)
        pager = view.pager
        tab.setupWithViewPager(pager)
        adapter = InfoPagerAdapter(activity!!.supportFragmentManager)
        pager.adapter = adapter
        pager.currentItem = 0
        return view
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach()")
        pager.currentItem = 0
    }

    companion object {
        @JvmStatic
        fun newInstance(data : CultureRow) = InfoFragment().apply {
            //this.cultureData = data
        }
    }

}
