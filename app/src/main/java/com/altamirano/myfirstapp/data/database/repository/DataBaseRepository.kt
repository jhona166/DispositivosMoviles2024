package com.altamirano.myfirstapp.data.database.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.altamirano.myfirstapp.data.database.dao.UsersDAO
import com.altamirano.myfirstapp.data.database.entities.UsersDB

@Database(
    entities =[UsersDB::class],
    version= 1
)
abstract class DataBaseRepository:RoomDatabase() {
    abstract fun getUserDao(): UsersDAO

}