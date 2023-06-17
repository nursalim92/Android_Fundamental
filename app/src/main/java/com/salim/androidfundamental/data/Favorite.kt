package com.salim.androidfundamental.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite_user")
data class Favorite(
    @field:SerializedName("login")
    val login : String? = null,

    @field:SerializedName("id")
    @PrimaryKey
    val id : Int? = null,

    @field:SerializedName("avatar_url")
    val avatar_url : String? = null

) : Parcelable
