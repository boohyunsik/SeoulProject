package com.caucse.seoulproject.helper.table

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Favorite {
    @PrimaryKey
    private lateinit var cultureKey : String

    @ColumnInfo(name="user_id")
    private lateinit var userId : String
}