package com.altamirano.myfirstapp.logic.usercases

import com.altamirano.myfirstapp.data.network.endpoints.NewsEndpoint
import com.altamirano.myfirstapp.data.network.entities.topNews.Data
import com.altamirano.myfirstapp.data.network.repository.RetrofitBase

class GetAllTopsNewUserCase {


    suspend operator fun invoke(): Result<List<Data>?> {

        var response = RetrofitBase.returnBaseRetrofitNews()
            .create(NewsEndpoint::class.java)
            .getAllTopsNews()
        return if (response.isSuccessful) {
            Result.success(response.body()?.data)
        } else {
            Result.failure(Exception("Ocurrio un error en la API"))
        }
    }
}


