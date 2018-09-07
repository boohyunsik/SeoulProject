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
import com.caucse.seoulproject.utils.ImageUtil
import com.caucse.seoulproject.viewmodel.MainViewModel
import com.jakewharton.rxbinding2.view.RxView
import com.squareup.picasso.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.coroutines.experimental.async
import java.lang.Exception


class CultureListAdapter(val view : RecyclerView
                         , val fragment: Fragment
                         , val mainViewModel: MainViewModel
                         , val context: Context) : RecyclerView.Adapter<RowHolder>() {
    private val TAG = "CultureListAdapter"

    private val inflater : LayoutInflater = LayoutInflater.from(view.context)
    private var data : ArrayList<CultureRow> = ArrayList<CultureRow>()

    private val icFavorite = R.drawable.ic_baseline_red_heart
    private val icNonFavorite = R.drawable.ic_baseline_black_heart

    init {
        Picasso.get().setIndicatorsEnabled(true)
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

        ImageUtil.setImage(holder.titleImageView, url)
        holder.titleTitleView.setText(data.TITLE)
        holder.titleGcode.setText(data.GCODE)

        setClickListener(holder, data)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setClickListener(holder: RowHolder, data: CultureRow) {
        RxView.clicks(holder.titleImageView)
                .subscribe {
                    Log.d(TAG, "click card view")
                    mainViewModel.curConcert = data
                    mainViewModel.addRecentData(data)
                    mainViewModel.fragmentManager.beginTransaction()
                            .addToBackStack(null)
                            .hide(fragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .add(R.id.frame_layout, InfoFragment.newInstance(data))
                            .commit()
                }
        RxView.clicks(holder.like)
                .subscribe {
                    var key = data.CULTCODE
                    if (mainViewModel.favoriteData.value?.containsKey(key)!!) {
                        mainViewModel.delFavoriteData(data)
                        holder.like.setImageResource(icNonFavorite)
                    } else {
                        mainViewModel.addFavoriteData(data)
                        holder.like.setImageResource(icFavorite)
                    }
                    mainViewModel.printFavoriteData()
                }
    }
}