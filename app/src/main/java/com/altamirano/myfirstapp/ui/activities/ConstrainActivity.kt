package com.altamirano.myfirstapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.altamirano.myfirstapp.data.network.entities.NewsDataUI
import com.altamirano.myfirstapp.data.network.entities.topNews.Data
import com.altamirano.myfirstapp.databinding.ActivityConstrainBinding
import com.altamirano.myfirstapp.logic.usercases.GetAllTopsNewUserCase
import com.altamirano.myfirstapp.ui.adapters.NewsAdapter
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
            {deleteItem(it)},
            {addItem()})

        binding.rvTopNews.adapter = newsAdapter
        binding.rvTopNews.layoutManager = LinearLayoutManager(
            this,LinearLayoutManager.VERTICAL,false
        )

    }

    private fun initListeners() {
        binding.refreshRV.setOnRefreshListener{
            initData()
            binding.refreshRV.isRefreshing = false
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
        val intent = Intent(this
            ,DetailItemActivity::class.java).apply{
            putExtra("id",news.id)
        }
        startActivity(intent)
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