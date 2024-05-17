package com.altamirano.myfirstapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.altamirano.myfirstapp.R
import com.altamirano.myfirstapp.R.layout.activity_constrain
import com.altamirano.myfirstapp.databinding.ActivityConstrainBinding
import com.altamirano.myfirstapp.databinding.ActivityMainBinding


class ConstrainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConstrainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityConstrainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var idUser: Int?=null

         intent.extras?.let{dato->
            var idUser = dato.getInt("idUser")



         }
            if(idUser != null){
                binding.editTextText2.text= idUser.toString()
            }
        else{
            startActivity(
                Intent(this,
                    MainActivity::class.java))
            }


    }
}
/*class ConstrainActivity : AppCompatActivity() {
   // private lateinit var binding: ActivityConstrainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* binding = ActivityConstrainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
*/
    }
/*
    private fun initListeners(){
       binding.btnLogin.setOnClickListener{
            var a = Intent(
                this,
                ConstrainActivity::class.java)
            startActivity(a)
        }

    }

    override fun onStart(){
        super.onStart()
        Log.d("UCE", "Metodo onStart")
    }

*/
}*/
