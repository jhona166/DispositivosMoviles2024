package com.altamirano.myfirstapp.data.network.endpoints

import com.altamirano.myfirstapp.data.network.entities.marvel.MarvelChars
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelEndpoint {
    @GET("v1/public/characters")
    suspend fun getAllcharacters(
                @Query("limit") limit:Int
    ): Response<MarvelChars>




}