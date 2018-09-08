package com.caucse.seoulproject.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.caucse.seoulproject.R
import com.caucse.seoulproject.utils.ImageUtil
import com.caucse.seoulproject.viewmodel.MainViewModel

class FavoriteListAdapter(private val mainViewModel: MainViewModel
                            , view: RecyclerView): RecyclerView.Adapter<RowHolder>() {
    val TAG = FavoriteListAdapter::class.java.simpleName
    private val inflater : LayoutInflater = LayoutInflater.from(view.context)

    override fun getItemCount(): Int {
        Log.d(TAG, "Item count = ${mainViewModel.favoriteData.value!!.size}")
        return mainViewModel.favoriteData.value!!.size
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder()")
        val titleView = holder.titleTitleView
        val imageView = holder.titleImageView
        val gcodeView = holder.titleGcode

        val culcode = mainViewModel.favoriteKeyList.value!![position]
        val data = mainViewModel.favoriteData.value!![culcode]

        titleView.text = data!!.TITLE
        ImageUtil.setImage(imageView, data.MAIN_IMG)
        gcodeView.text = data!!.GCODE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        Log.d(TAG, "onCreateViewHolder()")
        return RowHolder(inflater.inflate(R.layout.list_item, parent, false))
    }
}