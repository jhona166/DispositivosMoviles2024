package com.altamirano.myfirstapp.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.altamirano.myfirstapp.R
import com.altamirano.myfirstapp.databinding.ActivityDetailItemBinding

class DetailItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailItemBinding
    private var itemId:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras.let{
            itemId = it?.getString("id").toString()
        }
        binding.txtIdItem.text = itemId
    }
}