package com.altamirano.myfirstapp.logic.usercases

import com.altamirano.myfirstapp.data.network.endpoints.NewsEndpoint
import com.altamirano.myfirstapp.data.network.entities.NewsDataUI


import com.altamirano.myfirstapp.data.network.entities.topNews.Data
import com.altamirano.myfirstapp.data.network.repository.RetrofitBase
import com.altamirano.myfirstapp.ui.core.toNewsDataUI

class GetAllTopsNewUserCase {


    suspend operator fun invoke(): Result<List<NewsDataUI>> {

        var items = mutableListOf<NewsDataUI>()


        var response = RetrofitBase.returnBaseRetrofitNews()
            .create(NewsEndpoint::class.java)
            .getAllTopsNews()
        return if (response.isSuccessful) {
             response.body()?.data?.forEach {
              items.add(
                  it.toNewsDataUI()
                )

          }
            Result.success(items.toList())
        } else {
            Result.failure(Exception("Ocurrio un error en la API"))
        }
    }
}


