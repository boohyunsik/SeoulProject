package com.caucse.seoulproject.fragment

import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView

import com.caucse.seoulproject.R
import com.caucse.seoulproject.R.id.mapView
import com.caucse.seoulproject.data.CultureRow
import com.caucse.seoulproject.helper.NMapApiHelper
import com.nhn.android.maps.nmapdata.t
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.android.synthetic.main.fragment_info.view.*
import kotlinx.android.synthetic.main.nav_header_list.*
import com.nhn.android.maps.NMapActivity
import com.nhn.android.maps.NMapContext
import com.nhn.android.maps.NMapView
import java.sql.ClientInfoStatus


class InfoFragment : NMapFragment() {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var mMapView:NMapView
    private lateinit var mMapContext:NMapContext

    private lateinit var cultureData : CultureRow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view: View= inflater.inflate(R.layout.fragment_info, container, false)
        var readView:TextView = view.findViewById(R.id.readMoreTextView)
        var titleView:TextView = view.findViewById(R.id.titleView)
        var mMapView:NMapView = view.findViewById(R.id.mapView)
        var titleContent =  "타이틀입니다"
        var infoContent = """test"""
        var imageView: ImageView = view.findViewById(R.id.infoImageView)
        var mMapContext:NMapContext = NMapContext(super.getActivity())
        mMapContext.onCreate()

        titleView.setText(titleContent)
        readView.setText(infoContent)
        imageView.setImageResource(R.drawable.ic_test_info)
        mMapView.setClientId(getString(R.string.naver_client_key))
        mMapView.isClickable = true
        mMapView.isEnabled = true
        mMapView.isFocusable = true
        mMapView.isFocusableInTouchMode = true
        mMapView.requestFocus()

        mMapContext.setupMapView(mMapView)

        NMapApiHelper().getData(context, "강동아트센터 대극장 한강")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    // TODO: Rename method, update argument and hook method into UI event

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(data : CultureRow) = InfoFragment().apply {
            this.cultureData = data
        }
    }
}
