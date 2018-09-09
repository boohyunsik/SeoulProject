package com.caucse.seoulproject.fragment

/*
 * Copyright 2016 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nhn.android.maps.NMapContext
import com.nhn.android.maps.NMapView

/**
 * NMapFragment 클래스는 NMapActivity를 상속하지 않고 NMapView만 사용하고자 하는 경우에 NMapContext를 이용한 예제임.
 * NMapView 사용시 필요한 초기화 및 리스너 등록은 NMapActivity 사용시와 동일함.
 */
open class NMapFragment : Fragment() {

    private var mMapContext: NMapContext? = null

    /**
     * Fragment에 포함된 NMapView 객체를 반환함
     */
    private fun findMapView(v: View?): NMapView? {

        /*if (v !is ViewGroup) {
            return null
        }

        val vg = v as ViewGroup?
        if (vg is NMapView) {
            return vg
        }

        for (i in 0 until vg!!.childCount) {

            val child = vg.getChildAt(i) as? ViewGroup ?: continue

            val mapView = findMapView(child)
            if (mapView != null) {
                return mapView
            }
        }*/
        return null
    }

    /* Fragment 라이프사이클에 따라서 NMapContext의 해당 API를 호출함 */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //mMapContext = NMapContext(super.getActivity()!!)

        //mMapContext!!.onCreate()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        throw IllegalArgumentException("onCreateView should be implemented in the subclass of NMapFragment.")

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Fragment에 포함된 NMapView 객체 찾기
        //val mapView = findMapView(super.getView())
        //        ?: throw IllegalArgumentException("NMapFragment dose not have an instance of NMapView.")

        // NMapActivity를 상속하지 않는 경우에는 NMapView 객체 생성후 반드시 setupMapView()를 호출해야함.
        //mMapContext!!.setupMapView(mapView)
    }

    override fun onStart() {
        super.onStart()

        //mMapContext!!.onStart()
    }

    override fun onResume() {
        super.onResume()

        //mMapContext!!.onResume()
    }

    override fun onPause() {
        super.onPause()

        //mMapContext!!.onPause()
    }

    override fun onStop() {

        //mMapContext!!.onStop()

        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
       // mMapContext!!.onDestroy()

        super.onDestroy()
    }
}