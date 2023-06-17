package com.salim.androidfundamental.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.salim.androidfundamental.data.Favorite
import com.salim.androidfundamental.data.FavoriteDao
import com.salim.androidfundamental.database.FavoriteDatabase


class FavoriteRepositroy (application: Application) {
    private var mFavoriteDao: FavoriteDao?

    init {
        val dbFavorite = FavoriteDatabase.getDatabase(application)
        mFavoriteDao = dbFavorite.favoriteDao()
    }
    fun getAllFavorite(): LiveData<List<Favorite>>? = mFavoriteDao?.getAllFavorite()
    suspend fun insert(favorite: Favorite) = mFavoriteDao?.addFavorite(favorite)
    suspend fun delete(id: Int) = mFavoriteDao?.hapusFavorite(id)
    suspend fun cekFavorite(id: Int) = mFavoriteDao?.cekFavorite(id)


}