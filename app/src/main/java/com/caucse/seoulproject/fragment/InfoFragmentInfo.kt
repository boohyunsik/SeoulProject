package com.caucse.seoulproject.fragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView

import com.caucse.seoulproject.R
import com.caucse.seoulproject.data.CultureRow
import com.caucse.seoulproject.utils.ImageUtil
import com.caucse.seoulproject.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.list_item.*

class InfoFragmentInfo : Fragment() {
    private val TAG = this::class.java.simpleName
    private var cultureData : CultureRow? = null
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView()")
        var view: View = inflater.inflate(R.layout.fragment_info_fragment_info, container, false)
        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        cultureData = mainViewModel.curConcert
        var titleView : TextView = view.findViewById(R.id.infoTitleView)
        var titleContent =  cultureData!!.TITLE
        var infoContent = cultureData!!.CONTENTS
        var imageView: ImageView = view.findViewById(R.id.infoImageView)
        val myWebView = view.findViewById(R.id.infoWebView) as WebView
        mainViewModel.setTitle(titleContent)

        titleView.setText(titleContent)
        Log.d("title",titleContent)
        val url = cultureData!!.MAIN_IMG.toLowerCase()
        ImageUtil.setImage(imageView, url)

        val settings = myWebView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true

        myWebView.loadData(infoContent,"text/html","utf-8")

        return view
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
    fun getTitle() : String{
        return cultureData!!.TITLE
    }

    companion object {
        @JvmStatic
        fun newInstance() = InfoFragmentInfo().apply {
        }
    }
}
