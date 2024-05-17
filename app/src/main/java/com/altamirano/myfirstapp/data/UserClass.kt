package com.altamirano.myfirstapp.data

import android.util.Log

data class UserClass (var id : Int,
                      var name : String,
                      var pass : String,
    )

{
    init{
        Log.d("DATA","Se construye una nueva instancia de Usuarios")
    }


}

