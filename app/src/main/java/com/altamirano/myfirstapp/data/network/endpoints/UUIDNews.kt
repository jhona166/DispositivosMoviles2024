package com.altamirano.myfirstapp.data.network.endpoints

import com.altamirano.myfirstapp.data.network.entities.oneNews.OneNewsDataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UUIDNews {
    @GET("uuid/{uuid}")
    suspend fun getUUIDNews(@Path("uuid") uuid: String) : Response<OneNewsDataClass>
}