package com.salim.androidfundamental.data

import com.google.gson.annotations.SerializedName

data class DetailRespons(
    @field:SerializedName("login")
    val login : String? = null,

    @field:SerializedName("id")
    val id : Int? = null,

    @field:SerializedName("avatar_url")
    val avatar_url : String? = null,

    @field:SerializedName("followers_url")
    val followers_url : String? = null,

    @field:SerializedName("following_url")
    val following_url : String? = null,

    @field:SerializedName("name")
    val name : String? = null,

    @field:SerializedName("email")
    val email : String? = null,

    @field:SerializedName("followers")
    val followers : Int? = null,

    @field:SerializedName("following")
    val following : Int? = null,

    )
