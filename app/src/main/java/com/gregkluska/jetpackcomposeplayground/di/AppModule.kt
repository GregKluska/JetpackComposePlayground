package com.gregkluska.jetpackcomposeplayground.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.gregkluska.jetpackcomposeplayground.R
import com.gregkluska.jetpackcomposeplayground.api.ApiService
import com.gregkluska.jetpackcomposeplayground.util.Constants.Companion.BASE_URL
import com.gregkluska.jetpackcomposeplayground.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApiService( retrofitBuilder: Retrofit.Builder ) : ApiService {
        return retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }

//    @Singleton
//    @Provides
//    fun provideMainRepository(
//        apiService: ApiService
//    ) : MainRepository {
//        return MainRepository(apiService)
//    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
    }


    @Singleton
    @Provides
    fun provideGlideInstance(@ApplicationContext app: Context, requestOptions: RequestOptions): RequestManager {
        return Glide.with(app)
            .setDefaultRequestOptions(requestOptions)
    }

}