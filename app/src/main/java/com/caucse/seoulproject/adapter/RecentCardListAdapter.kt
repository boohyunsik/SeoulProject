package com.caucse.seoulproject.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.caucse.seoulproject.R
import com.caucse.seoulproject.utils.ImageUtil
import com.caucse.seoulproject.viewmodel.MainViewModel

class RecentCardListAdapter(val view : RecyclerView ,val mainViewModel: MainViewModel): RecyclerView.Adapter<RowHolder>() {

    private val TAG = "RecentCardListAdapter"
    private val inflater : LayoutInflater = LayoutInflater.from(view.context)

    override fun getItemCount(): Int {
        Log.d(TAG, Integer.toString(mainViewModel.getRecentDataItemCount()))
        return mainViewModel.getRecentDataItemCount()
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder()")
        val data = mainViewModel.getRecentDataItem(position)
        holder.titleTitleView.setText(data.TITLE)
        holder.titleGcode.setText(data.GCODE)
        ImageUtil.setImage(holder.titleImageView, data.MAIN_IMG.toLowerCase())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        return RowHolder(inflater.inflate(R.layout.list_item, parent, false))
    }
}