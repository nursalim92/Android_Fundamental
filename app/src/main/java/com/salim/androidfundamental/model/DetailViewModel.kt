package com.salim.androidfundamental.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.salim.androidfundamental.api.ApiClient
import com.salim.androidfundamental.data.DetailRespons
import com.salim.androidfundamental.data.Favorite
import com.salim.androidfundamental.data.FavoriteDao
import com.salim.androidfundamental.database.FavoriteDatabase
import com.salim.androidfundamental.repository.FavoriteRepositroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailViewModel (application: Application) : AndroidViewModel(application) {


private val repo  =  FavoriteRepositroy(application)
private var mFavoriteDao: FavoriteDao?
    private var mDatabase: FavoriteDatabase? = null

    init {
        val dbFavorite = FavoriteDatabase.getDatabase(application)
        mFavoriteDao = dbFavorite.favoriteDao()
    }

    val data = MutableLiveData<DetailRespons>()

    fun setDetailUsers(username: String) {
        ApiClient.apiInstance
            .getDetail(username)
            .enqueue(object : Callback<DetailRespons> {
                override fun onResponse(
                    call: Call<DetailRespons>,
                    response: Response<DetailRespons>
                ) {
                    if (response.isSuccessful){
                        data.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailRespons>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }


            })

    }

    fun getUsers() : LiveData<DetailRespons>{
        return data
    }

    fun addFavorite(id: Int, login: String?, avatar_url: String?) {
  CoroutineScope(Dispatchers.IO).launch {
      val favUser = Favorite(
          login,
          id,
          avatar_url
      )
      repo.insert(favUser)
  }
    }

    suspend fun cekFavorite(id: Int) = repo.cekFavorite(id)



    fun hapusFavorite(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {

        repo.delete(id)
        }
    }


}