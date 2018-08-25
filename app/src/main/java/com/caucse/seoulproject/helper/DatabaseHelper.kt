package com.caucse.seoulproject.helper

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.caucse.seoulproject.helper.dao.FavoriteDao
import com.caucse.seoulproject.helper.table.*

@Database(version = 1, entities = [User::class, Favorite::class])
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun getFavoriteDao(): FavoriteDao

    companion object {

        private var INSTANCE: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper? {
            if (INSTANCE == null) {
                synchronized(DatabaseHelper::class) {
                    INSTANCE = Room.databaseBuilder(
                            context,
                            DatabaseHelper::class.java,
                            "culture.db")
                            .build()
                }
            }
            return INSTANCE
        }
    }
}