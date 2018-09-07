package com.caucse.seoulproject.fragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.caucse.seoulproject.R
import com.caucse.seoulproject.data.CultureRow
import com.caucse.seoulproject.helper.NSearchApiHelper
import com.caucse.seoulproject.utils.ImageUtil
import com.caucse.seoulproject.viewmodel.MainViewModel
import com.nhn.android.maps.NMapContext
import com.nhn.android.maps.NMapController
import com.nhn.android.maps.NMapView
import com.nhn.android.maps.overlay.NMapPOIdata
import com.nhn.android.maps.overlay.NMapPOIitem
import com.nhn.android.mapviewer.overlay.NMapOverlayManager
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay
import com.nhn.android.mapviewer.overlay.NMapResourceProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


    class InfoFragment : NMapFragment() {

    val TAG = "InfoFragment"
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var mMapView: NMapView
    private lateinit var mMapContext: NMapContext
    private lateinit var mNMapController: NMapController
    private lateinit var mMapViewerResourceProvider:NMapViewerResourceProvider
    private lateinit var mOverlayManager:NMapOverlayManager
    private lateinit var onPOIdataStateChangeListener:NMapPOIdataOverlay.OnStateChangeListener
    private lateinit var poiData:NMapPOIdata
    private var cultureData : CultureRow? = null
    private lateinit var mainViewModel: MainViewModel
    private lateinit var poiDataOverlay:NMapPOIdataOverlay

    private lateinit var nMapResourceProvider: NMapResourceProvider
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        cultureData = mainViewModel.curConcert
        var view: View= inflater.inflate(R.layout.fragment_info, container, false)
        var readView:TextView = view.findViewById(R.id.readMoreTextView)
        var titleView:TextView = view.findViewById(R.id.titleView)
        var mMapView:NMapView = view.findViewById(R.id.mapView)
        var titleContent =  cultureData!!.TITLE
        var infoContent = cultureData!!.CONTENTS
        var imageView: ImageView = view.findViewById(R.id.infoImageView)
        var mMapContext:NMapContext = NMapContext(super.getActivity())
        var markerId = NMapPOIflagType.PIN
        var mMapViewerResourceProvider:NMapViewerResourceProvider= NMapViewerResourceProvider(activity)
        var poiData:NMapPOIdata = NMapPOIdata(2,mMapViewerResourceProvider)


        var mapX = 0
        var mapY = 0

        mMapContext.onCreate()
        mMapView.setClientId(getString(R.string.naver_client_key))
        mMapView.isClickable = true
        mMapView.isEnabled = true
        mMapView.isFocusable = true
        mMapView.isFocusableInTouchMode = true
        mMapView.requestFocus()
        titleView.setText(titleContent)
        readView.setText(infoContent)
        val url = cultureData!!.MAIN_IMG.toLowerCase()
        ImageUtil.setImage(imageView, url)

        val query = cultureData!!.PLACE.split(" ").get(0)
        NSearchApiHelper.getData(context, query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {result ->
                            Log.d(TAG, "search result = ${result}")
                           mapX = Integer.parseInt(result.getString("mapx"))
                            mapY = Integer.parseInt(result.getString("mapy"))
                        },
                        {error -> Log.d(TAG, "error -> " + error.message)},
                        {Log.d(TAG, "검색어 = ${query}")}
                )

        mMapContext.setupMapView(mMapView)

        var mOverlayManager:NMapOverlayManager = NMapOverlayManager(activity,mMapView,mMapViewerResourceProvider)
        var NMapPOIdatapoiData:NMapPOIdata = NMapPOIdata(2,mMapViewerResourceProvider)

        poiData.beginPOIdata(2)
        poiData.addPOIitem(127.061,37.51,"test",markerId,0)
        Log.d("Point",markerId.toString())
        poiData.endPOIdata()
        var poiDataOverlay:NMapPOIdataOverlay = mOverlayManager.createPOIdataOverlay(poiData,null)
        poiDataOverlay.showAllPOIdata(0)
        poiDataOverlay.onStateChangeListener



//        NMapApiHelper().getData(context, "강동아트센터")
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(
//                        {result -> Log.d(TAG, result)},
//                        {error -> Log.d(TAG, error.message)},
//                        {Log.d(TAG, "completed")}
//                )

        return view
    }

        fun onCallClick(poiDataOverlay:NMapPOIdataOverlay, item: NMapPOIitem){

        }
        fun onFocusChanged(poiDataOverlay:NMapPOIdataOverlay,item:NMapPOIitem){

        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


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
