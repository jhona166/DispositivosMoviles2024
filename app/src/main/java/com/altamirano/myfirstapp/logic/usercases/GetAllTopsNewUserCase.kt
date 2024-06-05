package com.altamirano.myfirstapp.logic.usercases

import com.altamirano.myfirstapp.data.network.endpoints.NewsEndpoint
import com.altamirano.myfirstapp.data.network.entities.NewsDataUI
import com.altamirano.myfirstapp.data.network.entities.topNews.Data
import com.altamirano.myfirstapp.data.network.repository.RetrofitBase

class GetAllTopsNewUserCase {


    suspend operator fun invoke(): Result<List<NewsDataUI>> {

        var items = ArrayList<NewsDataUI>()


        var response = RetrofitBase.returnBaseRetrofitNews()
            .create(NewsEndpoint::class.java)
            .getAllTopsNews()
        return if (response.isSuccessful) {
            val x = response.body()?.data
            x!!.forEach{
        items.add(NewsDataUI(
                 it.uuid
                ,it.url
                ,it.title
                ,it.image_url
                ,it.description
                )
                )
            }
            Result.success(items.toList())
        } else {
            Result.failure(Exception("Ocurrio un error en la API"))
        }
    }
}


