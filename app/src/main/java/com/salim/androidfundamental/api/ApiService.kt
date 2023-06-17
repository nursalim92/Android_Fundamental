package com.salim.androidfundamental.api

import com.salim.androidfundamental.data.DetailRespons
import com.salim.androidfundamental.data.User
import com.salim.androidfundamental.data.UserRespons
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET ("search/users" )
    @Headers ("Autorization: token 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")
    fun getUsers (
        @Query("q") query: String
    ): Call<UserRespons>


    @GET ("users/{username}" )
    @Headers ("Autorization: token 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")
    fun getDetail (
        @Path("username") name: String
    ): Call<DetailRespons>

    @GET ("users/{username}/followers" )
    @Headers ("Autorization: token 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")
    fun getFollowers (
        @Path("username") name: String
    ): Call<ArrayList<User>>

    @GET ("users/{username}/following" )
    @Headers ("Autorization: token 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")
    fun getFollowing (
        @Path("username") name: String
    ): Call<ArrayList<User>>

}