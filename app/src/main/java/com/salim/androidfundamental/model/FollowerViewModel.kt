package com.salim.androidfundamental.model

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salim.androidfundamental.api.ApiClient
import com.salim.androidfundamental.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FollowerViewModel: ViewModel() {
    val dataFollower = MutableLiveData<ArrayList<User>>()
    fun setDataFollower(username: String){
        ApiClient.apiInstance
            .getFollowers(username)
            .enqueue(object : Callback<ArrayList<User>>{
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful){
                        dataFollower.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun getDataFollower() :LiveData<ArrayList<User>>{
        return dataFollower
    }

}