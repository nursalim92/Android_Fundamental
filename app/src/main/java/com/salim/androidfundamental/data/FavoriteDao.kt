package com.salim.androidfundamental.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface FavoriteDao {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun addFavorite(dFavorite: Favorite)

    @Query("DELETE FROM favorite_user WHERE id = :id")
    suspend fun hapusFavorite(id: Int)


    @Query("SELECT COUNT(*) FROM favorite_user WHERE id = :id")
    suspend fun cekFavorite(id: Int): Int

    @Query("SELECT * FROM favorite_user ORDER BY id DESC")
    fun getAllFavorite(): LiveData<List<Favorite>>


}