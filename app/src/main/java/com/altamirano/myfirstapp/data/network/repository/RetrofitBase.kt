package com.altamirano.myfirstapp.data.network.repository

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBase {

    const val NEWS_API_KEY="aaqGrZKPhJpKBSnMnIGjB26Zv2sBIDj5YjMwDpok"
    fun returnBaseRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
           // .build()
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    }

    fun returnBaseRetrofitNews():Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.thenewsapi.com/v1/news/")
            // .build()
            .addConverterFactory(GsonConverterFactory.create())
            .client(apiClient())
            .build()

    }

    private fun apiClient():OkHttpClient =  OkHttpClient.Builder()
          .addInterceptor(NewsInterceptor(RetrofitBase.NEWS_API_KEY))
          .build()



}