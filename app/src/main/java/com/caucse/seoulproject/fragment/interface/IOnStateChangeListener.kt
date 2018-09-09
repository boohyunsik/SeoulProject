package com.caucse.seoulproject.fragment.`interface`

import android.util.Log
import com.nhn.android.maps.overlay.NMapPOIitem
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay

class IOnStateChangeListener: NMapPOIdataOverlay.OnStateChangeListener {
    val TAG = this::class.java.simpleName

    override fun onCalloutClick(p0: NMapPOIdataOverlay?, p1: NMapPOIitem?) {
        Log.d(TAG, "onCalloutClick")
    }

    override fun onFocusChanged(p0: NMapPOIdataOverlay?, p1: NMapPOIitem?) {
        Log.d(TAG, "onFocusChanged")
    }
}