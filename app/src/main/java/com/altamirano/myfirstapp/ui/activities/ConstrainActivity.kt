package com.altamirano.myfirstapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.altamirano.myfirstapp.R
import com.altamirano.myfirstapp.data.network.entities.NewsDataUI
import com.altamirano.myfirstapp.data.network.entities.topNews.Data
import com.altamirano.myfirstapp.databinding.ActivityConstrainBinding
import com.altamirano.myfirstapp.logic.usercases.GetAllTopsNewUserCase
import com.altamirano.myfirstapp.ui.adapters.NewsAdapter
import com.altamirano.myfirstapp.ui.fragments.FavoritesFragment
import com.altamirano.myfirstapp.ui.fragments.ListarNews
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ConstrainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConstrainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConstrainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // initVaribles()
        initListeners()
        //initData()

        val x = supportFragmentManager.beginTransaction()
        x.replace(binding.lytFragment.id,ListarNews())
        x.commit()
    }






//    private fun initRecyclerView() {
//
//        binding.rvTopNews.adapter = newsAdapter
//        binding.rvTopNews.layoutManager = LinearLayoutManager(
//            this,LinearLayoutManager.VERTICAL,false
//        )
//    }


    /*
    private fun initData(){
        binding.pgbarLoadData.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.IO) {
            val result = GetAllTopsNewUserCase().invoke()
            withContext(Dispatchers.Main){
                binding.pgbarLoadData.visibility = View.INVISIBLE
                result.onSuccess {
                    items = it.toMutableList()
                    newsAdapter.itemList = items
                    newsAdapter.notifiDataSetChange()
                }

                result.onFailure {
                    Snackbar.make(
                        binding.refreshRV,it.message.toString(),Snackbar.LENGTH_LONG
                    ).show()

                }


            }

        }

    }

*/
    private fun initListeners() {


        binding.bottomNavigation.setOnItemSelectedListener { item->
            when (item.itemId) {
                R.id.listarItem->{
                    val x = supportFragmentManager.beginTransaction()
                    x.replace(binding.lytFragment.id,ListarNews())
                    x.addToBackStack(null)
                    x.commit()
                    true
                }
                R.id.FavItem->{
                    val x = supportFragmentManager.beginTransaction()
                    x.replace(binding.lytFragment.id, FavoritesFragment())
                    x.addToBackStack(null)
                    x.commit()
                    true
                }
                R.id.NoFavItem->{
                    Snackbar.make(
                        binding.lytFragment,
                        "No Fav Item",
                        Snackbar.LENGTH_LONG)
                        .show()
                    true
                }
                else -> false
            }
        }
        binding.btnCloseSession.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }




}