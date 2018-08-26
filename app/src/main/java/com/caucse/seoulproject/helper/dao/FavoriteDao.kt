package com.caucse.seoulproject.helper.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.caucse.seoulproject.helper.table.Favorite
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    fun loadAll(): LiveData<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<Favorite>)

    @Insert
    fun insert(items: Favorite)

    @Query("SELECT * FROM favorite WHERE user_id = :id")
    fun load(id: String): LiveData<Favorite>

    @Query("SELECT * FROM favorite WHERE user_id=:id AND culture_code=:code")
    fun load(id: String, code: String): Single<Favorite>

    @Query("DELETE FROM favorite WHERE user_id=:id AND culture_code=:code")
    fun delete(id:String, code: String)

    @Query("SELECT count(*) FROM favorite")
    fun getCount(): Int
}