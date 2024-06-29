package com.altamirano.myfirstapp.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.altamirano.myfirstapp.R
import com.altamirano.myfirstapp.data.database.entities.UsersDB
import com.altamirano.myfirstapp.data.database.repository.DataBaseRepository
import com.altamirano.myfirstapp.databinding.ActivityDatabaseBinding
import com.altamirano.myfirstapp.ui.core.MyApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseActivity : AppCompatActivity() {

    private lateinit var  binding:ActivityDatabaseBinding
    private lateinit var conn:DataBaseRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()
        initListener()

    }

    private fun initListener(){

       binding.btnSave.setOnClickListener {
           val user = binding.etxtUser.text.toString()
           val pass = binding.etxtPass.text.toString()
           val userDb = UsersDB(name=user,password = pass)

           lifecycleScope.launch(Dispatchers.IO){
               conn.getUserDao().saveUser(listOf(userDb))
           }

       }
    }
    private fun initVariables(){
        conn = MyApplication.getDBConnection()
    }
}