package com.altamirano.myfirstapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.altamirano.myfirstapp.data.repository.ListUsers
import com.altamirano.myfirstapp.userCase.LoginUserCase

import com.altamirano.myfirstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            initListeners()

        }

        private fun initListeners() {
            binding.btnLogin.setOnClickListener {

                var loginUserCase= LoginUserCase(ListUsers())

                   var result = loginUserCase(
                    binding.etxtUser.text.toString(),
                    binding.etxtPassword.text.toString()
                    )

                result.onSuccess{user->
                    var intentToConstrainActivity = Intent(
                        this,
                        ConstrainActivity::class.java
                    ).apply{
                        putExtra("idUser",user.id)
                    }

                    startActivity(intentToConstrainActivity)
                }
                result.onFailure {
                    Toast.makeText(
                        this,
                        it.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }

        }

        override fun onStart() {
            super.onStart()
            Log.d("UCE", "Metodo onStart")
        }
    }
