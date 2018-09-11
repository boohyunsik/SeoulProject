package com.caucse.seoulproject.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.caucse.seoulproject.R
import com.caucse.seoulproject.data.CultureRow
import com.caucse.seoulproject.fragment.`interface`.IOnStateChangeListener
import com.caucse.seoulproject.helper.NSearchApiHelper
import com.caucse.seoulproject.utils.GeoTrans
import com.caucse.seoulproject.utils.GeoTransPoint
import com.caucse.seoulproject.viewmodel.MainViewModel
import com.nhn.android.maps.NMapContext
import com.nhn.android.maps.NMapController
import com.nhn.android.maps.NMapView
import com.nhn.android.maps.maplib.NGeoPoint
import com.nhn.android.maps.nmapmodel.NMapError
import com.nhn.android.maps.overlay.NMapPOIdata
import com.nhn.android.mapviewer.overlay.NMapOverlayManager
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_info_fragment_map.view.*

class InfoFragmentMap : NMapFragment(), NMapView.OnMapStateChangeListener {
    val TAG = this::class.java.simpleName

    private lateinit var nMapView: NMapView
    private var cultureData : CultureRow? = null
    private lateinit var mainViewModel: MainViewModel

    private lateinit var nMapViewerResourceProvider: NMapViewerResourceProvider
    private lateinit var nMapOverlayManager: NMapOverlayManager
    private lateinit var nMapController: NMapController
    private lateinit var nMapContext: NMapContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        nMapContext = NMapContext(activity)
        nMapContext.onCreate()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_info_fragment_map, container, false)
        Log.d(TAG, "onCreateView()")
        cultureData = mainViewModel.curConcert

        nMapView = view.naver_mapView
        nMapView.setClientId(getString(R.string.naver_client_key))
        nMapContext.setupMapView(nMapView)

        val query = "02-" + cultureData!!.PLACE
        NSearchApiHelper.getData(context, query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {result ->
                            Log.d(TAG, "search result = ${result}")
                            val mapX = result.getString("mapx").toInt()
                            val mapY = result.getString("mapy").toInt()
                            moveMapCenter(mapX, mapY)
                        },
                        {error ->
                            Log.d(TAG, "error -> " + error.message + ", 검색어 = $query")},
                        {Log.d(TAG, "검색어 = ${query}")}
                )
        return view
    }

    override fun onStart() {
        super.onStart()
        nMapContext.onStart()
        Log.d(TAG, "onStart() -> ${nMapView}")
        nMapView.isClickable = true
        nMapView.isEnabled = true
        nMapView.isFocusable = true
        nMapView.isFocusableInTouchMode = true
        nMapView.displayZoomControls(true)
        nMapView.requestFocus()

        nMapView.setBuiltInZoomControls(true, null)
        nMapView.setOnMapStateChangeListener(this)
        nMapController = nMapView.mapController
        nMapViewerResourceProvider = NMapViewerResourceProvider(activity)
        nMapOverlayManager = NMapOverlayManager(activity, nMapView, nMapViewerResourceProvider)
    }

    fun moveMapCenter(x: Int, y: Int) {
        Log.d(TAG, "moveMapCenter($x, $y)")
        val katec = GeoTransPoint(x.toDouble(), y.toDouble())
        val oGeo = GeoTrans.convert(GeoTrans.KATEC, GeoTrans.GEO, katec)
        val lat: Double = oGeo.getY()
        val lng: Double = oGeo.getX()
        Log.d(TAG, "lat = ${lat}, long = ${lng}")
        val currentPoint = NGeoPoint(lat, lng)
        nMapController.mapCenter = currentPoint

        val poiData = NMapPOIdata(2, nMapViewerResourceProvider)
        poiData.beginPOIdata(1)
        poiData.addPOIitem(lng, lat,"example", NMapPOIflagType.PIN, 0)
        poiData.endPOIdata()

        val poiDataOverlay: NMapPOIdataOverlay = nMapOverlayManager.createPOIdataOverlay(poiData, null)
        poiDataOverlay.showAllPOIdata(0)
        poiDataOverlay.onStateChangeListener = IOnStateChangeListener()
    }

    override fun onAnimationStateChange(p0: NMapView?, p1: Int, p2: Int) {
        Log.d(TAG, "onAnimationStateChange()")
    }

    override fun onMapCenterChange(p0: NMapView?, p1: NGeoPoint?) {
        Log.d(TAG, "onMapCenterChange()")
    }

    override fun onMapCenterChangeFine(p0: NMapView?) {
        Log.d(TAG, "onMapCenterChangeFine()")
    }

    override fun onMapInitHandler(nMapView: NMapView?, nMapError: NMapError?) {
        if (nMapError == null) {
            Log.d(TAG, "map init success")
        } else {
            Log.e(TAG, "map init error : ${nMapError.message}")
        }
    }

    override fun onZoomLevelChange(p0: NMapView?, p1: Int) {
        Log.d(TAG, "onZoomLevelChange()")
    }

    override fun onResume() {
        Log.d(TAG, "onResume()")
        super.onResume()
        nMapContext.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause()")
        super.onPause()
        nMapContext.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop()")
        super.onStop()
        nMapContext.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy()")
        nMapContext.onDestroy()
        super.onDestroy()
    }
}
