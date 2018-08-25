package com.caucse.seoulproject.helper.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.caucse.seoulproject.helper.table.Favorite

interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    fun loadAll(): LiveData<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<Favorite>)

    @Query("SELECT * FROM favorite WHERE user_id = :id")
    fun load(id: Int): LiveData<Favorite>
}