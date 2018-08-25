package com.caucse.seoulproject.helper.table

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class User {
    @PrimaryKey
    lateinit var userId: String
}