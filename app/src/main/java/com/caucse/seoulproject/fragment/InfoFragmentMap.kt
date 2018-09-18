package com.caucse.seoulproject.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
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
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
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

class InfoFragmentMap : Fragment(), OnMapReadyCallback {
    val TAG = this::class.java.simpleName

    private lateinit var nMapView: NMapView
    private var cultureData : CultureRow? = null
    private lateinit var mainViewModel: MainViewModel
    private var googleMap: GoogleMap? = null
    private lateinit var mapView: MapView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (mapView != null) {
            mapView.onCreate(savedInstanceState)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_info_fragment_map, container, false)
        Log.d(TAG, "onCreateView()")
        cultureData = mainViewModel.curConcert

        val locationTextView = view.map_location
        val telTextView = view.map_tel

        locationTextView.text = cultureData!!.PLACE
        telTextView.text = cultureData!!.INQUIRY
        mapView = view.mapView
        mapView.getMapAsync(this)
        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        Log.d(TAG, "onMapReady()")
        val sydney: LatLng = LatLng(37.56, 126.97)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker is Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(13f))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume()")
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause()")
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop()")
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy()")
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
