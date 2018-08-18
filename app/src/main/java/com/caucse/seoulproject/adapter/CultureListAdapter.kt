package com.caucse.seoulproject.adapter

import com.caucse.seoulproject.R
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caucse.seoulproject.data.CultureRow
import com.squareup.picasso.Picasso
import com.squareup.picasso.PicassoProvider
import kotlinx.android.synthetic.main.list_item.view.*


class CultureListAdapter(view : RecyclerView) : RecyclerView.Adapter<RowHolder>() {
    private val TAG = "CultureListAdapter"
    private val inflater : LayoutInflater = LayoutInflater.from(view.context)
    private var data : ArrayList<CultureRow> = ArrayList<CultureRow>()

    fun addData(e : List<CultureRow>) {
        data.addAll(e)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        return RowHolder(inflater.inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        val data : CultureRow = data.get(position)
        val url = data.MAIN_IMG.toLowerCase()
        val title = data.TITLE
        try {
            Picasso.get().load(url)
                    .fit()
                    .into(holder.titleImageView)
        } catch (e: Exception) {
            Log.d(TAG, e.message)
        }
        holder.titleTitleView.setText(title)

    }

    override fun getItemCount(): Int {
        return data.size
    }

}

class RowHolder (row: View) : RecyclerView.ViewHolder(row) {
    var cardView = row.card_view
    var titleImageView = row.title_image
    var titleTitleView = row.title_title
}