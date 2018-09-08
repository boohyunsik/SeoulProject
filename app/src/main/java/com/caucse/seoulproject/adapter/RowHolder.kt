package com.caucse.seoulproject.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item.view.*

class RowHolder (row: View) : RecyclerView.ViewHolder(row) {
    var cardView: CardView = row.card_view
    var titleImageView: ImageView = row.title_image
    var titleTitleView: TextView = row.title_title
    var titleGcode: TextView = row.title_gcode
    var like: ImageView = row.like_but
}