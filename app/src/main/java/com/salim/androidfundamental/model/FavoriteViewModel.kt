package com.salim.androidfundamental.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.salim.androidfundamental.data.Favorite
import com.salim.androidfundamental.repository.FavoriteRepositroy


class FavoriteViewModel (application: Application) : AndroidViewModel(application) {
    private val favoriteRepo = FavoriteRepositroy(application)

    val favoriteData = favoriteRepo.getAllFavorite()

    fun getAllFavorite() : LiveData<List<Favorite>>?{
        return favoriteData
    }
}