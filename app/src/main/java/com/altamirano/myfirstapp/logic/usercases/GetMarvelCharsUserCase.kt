package com.altamirano.myfirstapp.logic.usercases

import com.altamirano.myfirstapp.data.network.endpoints.MarvelEndpoint
import com.altamirano.myfirstapp.data.network.entities.marvel.MarvelChars
import com.altamirano.myfirstapp.data.network.repository.RetrofitBase

class GetMarvelCharsUserCase {

    suspend operator fun invoke():Result<MarvelChars?>{
        var response=RetrofitBase.returnBaseRetrofitMarvel()
            .create(MarvelEndpoint::class.java)
            .getAllcharacters(2)

        return if(response.isSuccessful) {
            val x = response.body()
            Result.success(x)
        }else{
            Result.failure(Exception("Ocurrio un error en la API"))
        }
    }

}