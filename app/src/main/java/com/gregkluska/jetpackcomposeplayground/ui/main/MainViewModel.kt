package com.gregkluska.jetpackcomposeplayground.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gregkluska.jetpackcomposeplayground.api.GenericApiResponse
import com.gregkluska.jetpackcomposeplayground.models.Post
import com.gregkluska.jetpackcomposeplayground.repository.main.MainRepository

class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun testPostListRequest(): LiveData<GenericApiResponse<List<Post>>> {
        return mainRepository.testPhotoListRequest()
    }
}