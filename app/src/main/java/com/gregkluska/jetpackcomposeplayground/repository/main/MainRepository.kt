package com.gregkluska.jetpackcomposeplayground.repository.main

import androidx.lifecycle.LiveData
import com.gregkluska.jetpackcomposeplayground.api.ApiService
import com.gregkluska.jetpackcomposeplayground.api.GenericApiResponse
import com.gregkluska.jetpackcomposeplayground.models.Post
import javax.inject.Inject

class MainRepository
@Inject
constructor(
    val apiService: ApiService
) {

    fun testPhotoListRequest(): LiveData<GenericApiResponse<List<Post>>> {
        return apiService.getPosts()
    }

}