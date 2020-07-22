package com.gregkluska.jetpackcomposeplayground.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post(
    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("title")
    val title: String,

    @Expose
    @SerializedName("body")
    val body: String
) {
    override fun toString(): String {
        return "Post(id=$id, title='$title', body='$body')"
    }
}