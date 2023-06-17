package com.salim.androidfundamental.data

import com.google.gson.annotations.SerializedName

data class User(
    @field:SerializedName("login")
    val login : String? = null,

    @field:SerializedName("id")
    val id : Int? = null,

    @field:SerializedName("avatar_url")
    val avatar_url : String? = null
)
