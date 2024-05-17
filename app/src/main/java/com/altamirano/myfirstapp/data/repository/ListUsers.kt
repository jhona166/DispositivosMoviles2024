package com.altamirano.myfirstapp.data.repository

import com.altamirano.myfirstapp.data.UserClass

class ListUsers {

        private var listUsers =  listOf(

            UserClass(1,"admin","admin"),
            UserClass(2,"goku","kakaroto"),
            UserClass(3,"jaimito","jaimito")
        )

    fun checkUserPassword(user:String,pass:String):UserClass?{

        var usReturn:UserClass?=null
        listUsers.forEach{us->
            if(us.name == user && us.pass==pass){
                usReturn = us
            }
        }
        return usReturn
    }

    fun checkUserPassword2(user:String,pass:String):UserClass?{

      return listUsers.filter{us->
           us.name==user && us.pass==pass
       }.first()

    }


}