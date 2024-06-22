package com.altamirano.myfirstapp.data.network.repository

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBase {

    const val NEWS_API_KEY="aaqGrZKPhJpKBSnMnIGjB26Zv2sBIDj5YjMwDpok"
    const val PUBLIC_KEY_MARVEL="key837c4192e7f937e880e527f8645c68e0"
    const val TS_MARVEL=120
    const val HASH_MARVEL="e3989b3e6ba96fab504bd16499ee6f4a"
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
            .addConverterFactory(GsonConverterFactory.create())
            .client(apiClient())
            .build()

    }




    private fun apiClient():OkHttpClient =  OkHttpClient.Builder()
          .addInterceptor(NewsInterceptor(RetrofitBase.NEWS_API_KEY))
          .build()

    fun returnBaseRetrofitMarvel():Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443/v1/public/")
            // .build()
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    private fun apiClientMarvel():OkHttpClient =  OkHttpClient.Builder()
        .addInterceptor(
            MarvelInterceptor(PUBLIC_KEY_MARVEL,
                            TS_MARVEL,
                            HASH_MARVEL
            )
        )
        .build()

}