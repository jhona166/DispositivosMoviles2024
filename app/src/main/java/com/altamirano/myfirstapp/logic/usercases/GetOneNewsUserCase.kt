package com.altamirano.myfirstapp.logic.usercases

import com.altamirano.myfirstapp.data.network.endpoints.UUIDNews
import com.altamirano.myfirstapp.data.network.entities.oneNews.OneNewsDataClass
import com.altamirano.myfirstapp.data.network.repository.RetrofitBase

class GetOneNewsUserCase {
    suspend operator fun invoke(uuid: String): Result<OneNewsDataClass?> {

        var response = RetrofitBase.returnBaseRetrofitNews()
            .create(UUIDNews::class.java)
            .getUUIDNews(uuid)
        return if (response.isSuccessful) {
            Result.success(response.body())
        } else {
            Result.failure(Exception("Error en la API"))
        }
    }
}