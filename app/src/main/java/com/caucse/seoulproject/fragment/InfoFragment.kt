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
import android.widget.ScrollView
import android.widget.TextView

import com.caucse.seoulproject.R
import com.caucse.seoulproject.data.CultureRow
import com.caucse.seoulproject.helper.NSearchApiHelper
import com.caucse.seoulproject.utils.ImageUtil
import com.caucse.seoulproject.viewmodel.MainViewModel
import com.nhn.android.maps.NMapContext
import com.nhn.android.maps.NMapView
import com.nhn.android.mapviewer.overlay.NMapResourceProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_info.*


class InfoFragment : NMapFragment() {

    val TAG = "InfoFragment"
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var mMapView: NMapView
    private lateinit var mMapContext: NMapContext

    private var cultureData : CultureRow? = null
    private lateinit var mainViewModel: MainViewModel

    private lateinit var nMapResourceProvider: NMapResourceProvider
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        cultureData = mainViewModel.curConcert
        val view: View= inflater.inflate(R.layout.fragment_info, container, false)
        val readView:TextView = view.findViewById(R.id.readMoreTextView)
        val titleView:TextView = view.findViewById(R.id.titleView)
        val mMapView:NMapView = view.findViewById(R.id.mapView)
        val titleContent =  cultureData!!.TITLE
        val infoContent = cultureData!!.CONTENTS
        val imageView: ImageView = view.findViewById(R.id.infoImageView)
        val mMapContext:NMapContext = NMapContext(super.getActivity())
        val scrollView: ScrollView = view.findViewById(R.id.scrollView2)
        scrollView.smoothScrollTo(0,0)

        mMapContext.onCreate()

        titleView.setText(titleContent)
        readView.setText(infoContent)
        val url = cultureData!!.MAIN_IMG.toLowerCase()
        ImageUtil.setImage(imageView, url)

        mMapView.setClientId(getString(R.string.naver_client_key))
        mMapView.isClickable = true
        mMapView.isEnabled = true
        mMapView.isFocusable = true
        mMapView.isFocusableInTouchMode = true
        mMapView.requestFocus()

        mMapContext.setupMapView(mMapView)

        val query = cultureData!!.PLACE.split(" ").get(0)
        NSearchApiHelper.getData(context, query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {result ->
                            Log.d(TAG, "search result = ${result}")
                            result.getString("mapx")
                            result.getString("mapy")
                        },
                        {error -> Log.d(TAG, "error -> " + error.message)},
                        {Log.d(TAG, "검색어 = ${query}")}
                )

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
