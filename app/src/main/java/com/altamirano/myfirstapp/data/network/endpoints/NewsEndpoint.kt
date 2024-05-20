package com.altamirano.myfirstapp.data.network.endpoints

import com.altamirano.myfirstapp.data.network.entities.news.NewsApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsEndpoint {
    @GET("top")
    suspend fun getAllTopsNews
                (@Query("api_token") apiToken:String
    ):Response<NewsApi>


}