package com.salim.androidfundamental.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salim.androidfundamental.api.ApiClient
import com.salim.androidfundamental.data.User
import com.salim.androidfundamental.data.UserRespons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel : ViewModel() {



    val listData = MutableLiveData<ArrayList<User>?>()
    fun setUsers(query: String) {
        ApiClient.apiInstance
            .getUsers(query)
            .enqueue(object : Callback<UserRespons>{
                override fun onResponse(call: Call<UserRespons>, response: Response<UserRespons>) {
                   if (response.isSuccessful){
                       listData.postValue(response.body()?.items)
                   }

                }

                override fun onFailure(call: Call<UserRespons>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })

    }


    fun getUser() : LiveData<ArrayList<User>?>{
        return listData
    }




}