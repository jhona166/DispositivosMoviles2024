package com.altamirano.myfirstapp.data.network.repository

import okhttp3.Interceptor
import okhttp3.Response

class NewsInterceptor(private val apiKey:String):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val newUrl = chain.request()
            .url().newBuilder()
            .addQueryParameter("api_token",apiKey)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()


      /*  when(x.code()){
            409->{}
            401->{}
            403->{}
            405->{}
            else->{

            }
        }
        if(x.code()==409){
           val x1 = request.newBuilder().header(
                "dsahdkj","sckdhsfkjs"
            ).build()
            val token = chain.proceed(x1).body().toString()
        }
        else {
            TODO("Codigo funcional para solicitudes normales")
        }*/
        return chain.proceed(newRequest)
    }

}