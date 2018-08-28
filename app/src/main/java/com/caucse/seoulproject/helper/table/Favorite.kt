package com.caucse.seoulproject.helper.table

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName="favorite")
class Favorite {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Int = 0

    @ColumnInfo(name="culture_code")
    lateinit var cultureCode : String

    @ColumnInfo(name="user_id")
    lateinit var userId : String
}