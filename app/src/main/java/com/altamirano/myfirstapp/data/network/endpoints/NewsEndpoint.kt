package com.altamirano.myfirstapp.data.network.endpoints

import com.altamirano.myfirstapp.data.network.entities.topNews.NewsApi
import com.altamirano.myfirstapp.data.network.entities.allNews.AllNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsEndpoint {
    @GET("top")
    suspend fun getAllTopsNews(): Response<NewsApi>

    @GET("all")
    suspend fun getAllNews(): Response<AllNews>




}