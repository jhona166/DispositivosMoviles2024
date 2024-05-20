package com.altamirano.myfirstapp.userCase

import com.altamirano.myfirstapp.data.UserClass
import com.altamirano.myfirstapp.data.local.repository.ListUsers

class LoginUserCase (val listUser: ListUsers) {
    operator fun invoke(
        user: String,
        password:String
    ):Result<UserClass>{
       var us = listUser.checkUserPassword(user,password)
       return if(us!=null){
          Result.success(us)

        }else{
           return  Result.failure(Exception("Error de usuario o contraseña"))
        }

    }
}