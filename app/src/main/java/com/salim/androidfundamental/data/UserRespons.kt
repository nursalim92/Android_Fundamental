package com.salim.androidfundamental.data

import com.google.gson.annotations.SerializedName

data class UserRespons(
    @field:SerializedName("items")
    val items : ArrayList<User>? = null

)
