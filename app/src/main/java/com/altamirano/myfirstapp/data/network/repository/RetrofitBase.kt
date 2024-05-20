package com.altamirano.myfirstapp.data.network.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBase {

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
            .build()

    }


}