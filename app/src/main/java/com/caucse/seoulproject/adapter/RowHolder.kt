package com.caucse.seoulproject.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.list_item.view.*

class RowHolder (row: View) : RecyclerView.ViewHolder(row) {
    var cardView = row.card_view
    var titleImageView = row.title_image
    var titleTitleView = row.title_title
    var like = row.like_but
}