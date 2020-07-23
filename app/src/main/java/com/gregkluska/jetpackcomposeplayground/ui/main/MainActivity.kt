package com.gregkluska.jetpackcomposeplayground.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.Observer
import androidx.ui.core.*
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.Card
import androidx.ui.material.MaterialTheme
import androidx.ui.res.imageResource
import androidx.ui.text.style.TextOverflow
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.gregkluska.jetpackcomposeplayground.R
import com.gregkluska.jetpackcomposeplayground.api.ApiEmptyResponse
import com.gregkluska.jetpackcomposeplayground.api.ApiErrorResponse
import com.gregkluska.jetpackcomposeplayground.api.ApiSuccessResponse
import com.gregkluska.jetpackcomposeplayground.models.Post
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeObservers()
    }

    private fun subscribeObservers() {

        viewModel.testPostListRequest().observe(this, Observer { response -> 
            when (response) {
                is ApiSuccessResponse -> {
                    Log.d(TAG, "subscribeObservers: Success response : ${response.body}")

                    setContent {
                        PopulateList(response.body)
                    }
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
fun NewsStory(title: String, body: String) {
    val image = imageResource(R.drawable.header)
    MaterialTheme {
        val typography = MaterialTheme.typography
        Column() {
            val imageModifier = Modifier
                .preferredHeightIn(maxHeight = 180.dp)
                .fillMaxWidth()


            Column (
                modifier = Modifier.padding(2.dp)
            ) {
                Image(image, modifier = imageModifier, contentScale = ContentScale.Crop)
                Column (
                        modifier = Modifier.padding(8.dp)
                ) {
                    Text(title,
                            style = typography.h6,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis)
                    Text(body, style = typography.body2)
                    Text("December 2018", style = typography.body2)
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    NewsStory("This is the title", "Lorem Ipsum Body")
}

@Composable
fun PopulateList(posts: List<Post>) {
    VerticalScroller {
        Column {
            posts.forEach {
                NewsStory(it.title, it.body)
            }
        }
    }
}