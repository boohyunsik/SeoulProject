package com.caucse.seoulproject.helper.table

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Favorite {
    @PrimaryKey
    @ColumnInfo(name="culture_code")
    lateinit var cultureCode : String

    @ColumnInfo(name="user_id")
    lateinit var userId : String
}