package com.gregkluska.jetpackcomposeplayground.api

import androidx.lifecycle.LiveData
import com.gregkluska.jetpackcomposeplayground.models.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    fun getPosts(): LiveData<GenericApiResponse<List<Post>>>

}