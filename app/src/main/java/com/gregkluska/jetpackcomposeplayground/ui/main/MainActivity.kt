package com.gregkluska.jetpackcomposeplayground.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.Observer
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.core.clip
import androidx.ui.core.setContent
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.res.imageResource
import androidx.ui.text.style.TextOverflow
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.gregkluska.jetpackcomposeplayground.R
import com.gregkluska.jetpackcomposeplayground.api.ApiEmptyResponse
import com.gregkluska.jetpackcomposeplayground.api.ApiErrorResponse
import com.gregkluska.jetpackcomposeplayground.api.ApiSuccessResponse
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewsStory()
        }

        subscribeObservers()
    }

    private fun subscribeObservers() {

        viewModel.testPostListRequest().observe(this, Observer { response -> 
            when (response) {
                is ApiSuccessResponse -> {
                    Log.d(TAG, "subscribeObservers: Success response : ${response.body}")
                }
                
                is ApiErrorResponse -> {
                    Log.d(TAG, "subscribeObservers: Error response : ${response.errorMessage}")
                }
                
                is ApiEmptyResponse -> {
                    Log.d(TAG, "subscribeObservers: Empty response")
                }
            }
        })

    }

}

@Composable
fun NewsStory() {
    val image = imageResource(R.drawable.header)
    MaterialTheme {
        val typography = MaterialTheme.typography
        Column (
            modifier = Modifier.padding(16.dp)
        )
        {
            val imageModifier = Modifier
                .preferredHeightIn(maxHeight = 180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(4.dp))

            Image(image, modifier = imageModifier, contentScale = ContentScale.Crop)
            Spacer(Modifier.preferredHeight(16.dp))
            Text("A day in Shark Fin Cove",
                style = typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)
            Text("Davenport, California",
                style = typography.body2)
            Text("December 2018",
                style = typography.body2)
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    NewsStory()
}