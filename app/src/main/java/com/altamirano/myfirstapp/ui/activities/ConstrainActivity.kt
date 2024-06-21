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
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ConstrainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConstrainBinding
    private var items:MutableList<NewsDataUI> = mutableListOf()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConstrainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVaribles()
        initListeners()
        initData()


    }

    private fun initVaribles() {
        newsAdapter = NewsAdapter(
            {descriptionItem(it)},
            {deleteItem(it)})

        binding.rvTopNews.adapter = newsAdapter
        //Para ubicación de noticias da un formato con 2 columnas
       // binding.rvTopNews.layoutManager = GridLayoutManager(
         //   this,
            //2
        //)


        //Para ubicación de noticias da una noticia y se desplaza hacia la izq and dere
       //binding.rvTopNews.layoutManager = LinearLayoutManager(
         //   this,LinearLayoutManager.HORIZONTAL,false
        //)

        binding.rvTopNews.layoutManager = CarouselLayoutManager()

    }

    private fun initListeners() {
        binding.refreshRV.setOnRefreshListener{
            initData()
            binding.refreshRV.isRefreshing = false
        }
            binding.btnInsert.setOnClickListener{
                addItem()
            }
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.listarItem->{
                    startActivity(
                            Intent(this, DetailItemActivity::class.java))
                    //Snackbar.make(binding.refreshRV,"Item Listar",Snackbar.LENGTH_LONG).show()
                    true
                }
                R.id.FavItem->{
                    Snackbar.make(binding.refreshRV,"Fav Item",Snackbar.LENGTH_LONG).show()
                    true
                }
                R.id.NoFavItem->{
                    Snackbar.make(binding.refreshRV,"No Fav Item",Snackbar.LENGTH_LONG).show()
               true
                }
                else -> false
            }
        }
    }

    private fun initRecyclerView() {

        binding.rvTopNews.adapter = newsAdapter
        binding.rvTopNews.layoutManager = LinearLayoutManager(
            this,LinearLayoutManager.VERTICAL,false
        )
    }

    private fun initData() {
        binding.pgbarLoadData.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.IO) {

            val result = GetAllTopsNewUserCase().invoke()

            withContext(Dispatchers.Main) {

                binding.pgbarLoadData.visibility = View.INVISIBLE

                result.onSuccess {
                    items = it.toMutableList()
                    newsAdapter.itemList = items
                    newsAdapter.notifyDataSetChanged()
                }

                result.onFailure {
                   Snackbar.make(
                                binding.refreshRV,
                                it.message.toString(),
                                Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun descriptionItem(news: NewsDataUI) {
        //Una desripcion al pie de pagina
        Snackbar.make(binding.refreshRV,news.name,Snackbar.LENGTH_LONG).show()

//        val intent = Intent(this
//            ,DetailItemActivity::class.java).apply{
//            putExtra("id",news.id)
//        }
//        startActivity(intent)
    }

    private fun deleteItem(position:Int){

        items.removeAt(position)
        newsAdapter.itemList = items
        newsAdapter.notifyItemRemoved(position)

    }

    private fun addItem(){
        items.add(
            NewsDataUI("1",
                              "www.google.com",
                            "Noticia_mentira",
                            "sdgdsagds"
                            ,"Description_Fantasma"
                            ,"ES"))
        newsAdapter.itemList = items
        newsAdapter.notifyItemInserted(items.size-1)


    }


}