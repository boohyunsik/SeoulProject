package com.caucse.seoulproject.adapter

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.caucse.seoulproject.R
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caucse.seoulproject.data.CultureRow
import com.caucse.seoulproject.fragment.InfoFragment
import com.caucse.seoulproject.viewmodel.MainViewModel
import com.jakewharton.rxbinding2.view.RxView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.PicassoProvider
import kotlinx.android.synthetic.main.list_item.view.*


class CultureListAdapter(val view : RecyclerView
                         , val fragment: Fragment
                         , val fm: FragmentManager
                         , val mainViewModel: MainViewModel
                         , val context: Context) : RecyclerView.Adapter<RowHolder>() {
    private val TAG = "CultureListAdapter"

    private val inflater : LayoutInflater = LayoutInflater.from(view.context)
    private var data : ArrayList<CultureRow> = ArrayList<CultureRow>()
    private val callback: Callback = object: Callback {
        override fun onSuccess() {
            Log.d(TAG, "Picasso : onSuccess()")
        }

        override fun onError(e: java.lang.Exception?) {
            Log.d(TAG, "Picasso : onError() -> ${e?.message}")
        }
    }
    fun initData(e : List<CultureRow>) {
        data.clear()
        data.addAll(e)
        notifyDataSetChanged()
    }

    fun addData(e : List<CultureRow>) {
        data.addAll(e)
        notifyDataSetChanged()
    }

    fun addData(e : CultureRow) {
        data.add(e)
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
                    .into(holder.titleImageView, callback)
        } catch (e: Exception) {
            Log.d(TAG, e.message)
        }
        holder.titleTitleView.setText(title)

        var res: Int
        if (!mainViewModel.getIsFavorited(context, data.CULTCODE)) {
            res = R.drawable.baseline_favorite_border_black_18dp
        } else {
            res = R.drawable.baseline_favorite_black_18dp
        }
        holder.like.setBackgroundResource(res)

        RxView.clicks(holder.titleImageView)
                .subscribe {
                    Log.d(TAG, "click card view")
                    fm.beginTransaction()
                            .addToBackStack(null)
                            .hide(fragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .add(R.id.frame_layout, InfoFragment.newInstance(data))
                            .commit()
                }

        RxView.clicks(holder.like)
                .subscribe {
                    var key = data.CULTCODE
                }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

class RowHolder (row: View) : RecyclerView.ViewHolder(row) {
    var cardView = row.card_view
    var titleImageView = row.title_image
    var titleTitleView = row.title_title
    var like = row.like_but
}