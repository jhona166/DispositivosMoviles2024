package com.altamirano.myfirstapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.altamirano.myfirstapp.data.database.entities.UsersDB

@Dao
interface UsersDAO {
    @Query("select * from users")
    fun getAllUser():List<UsersDB>

    @Query("select * from users where id_user=:id")
    fun getUserById(id:Int):UsersDB

    @Insert
    fun saveUser(user: List<UsersDB>)

    @Delete
    fun deleteUser(user: UsersDB)




}