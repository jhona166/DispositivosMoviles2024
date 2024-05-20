package com.altamirano.myfirstapp.userCase

import com.altamirano.myfirstapp.data.network.endpoints.NewsEndpoint
import com.altamirano.myfirstapp.data.network.repository.RetrofitBase

class GetAllTopsNewUserCase {

    suspend operator fun invoke(){
       // val key= "aaqGrZKPhJpKBSnMnIGjB26Zv2sBIDj5YjMwDpok"

        var response = RetrofitBase.returnBaseRetrofitNews()
            .create(NewsEndpoint::class.java)
            .getAllTopsNews()


        if(response.isSuccessful){
            val data = response.body()?.data
        }

    }

}