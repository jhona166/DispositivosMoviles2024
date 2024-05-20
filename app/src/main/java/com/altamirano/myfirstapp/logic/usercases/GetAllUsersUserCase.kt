package com.altamirano.myfirstapp.logic.usercases

import android.util.Log
import com.altamirano.myfirstapp.data.network.endpoints.UsersEndpoint
import com.altamirano.myfirstapp.data.network.repository.RetrofitBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class GetAllUsersUserCase {
    suspend operator fun invoke() {
        withContext(Dispatchers.IO) {
            val call = RetrofitBase.returnBaseRetrofit()
            val service = call.create(UsersEndpoint::class.java)
            val response = service.getAllUsers()

            return@withContext if (response.isSuccessful) {
                val body = response.body()
                Log.d("RSP", body.toString())
                true
            } else {
                Log.d("RSP", "La ejecucion fallo")
                false
            }
        }
    }
}

