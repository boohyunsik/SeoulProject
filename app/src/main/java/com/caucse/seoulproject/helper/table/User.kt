package com.caucse.seoulproject.helper.table

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName="user")
class User {
    @PrimaryKey
    lateinit var userId: String
}