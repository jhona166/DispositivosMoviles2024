package com.altamirano.myfirstapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConstrainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
    }

    private fun initRecyclerView(items: List<NewsDataUI>) {
        binding.rvTopNews.adapter = NewsAdapter(items){showTitle(it)}
        binding.rvTopNews.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
    }

    private fun initData() {
        binding.pgbarLoadData.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.IO) {

            val resultItems = GetAllTopsNewUserCase().invoke()

            withContext(Dispatchers.Main) {

                binding.pgbarLoadData.visibility = View.INVISIBLE

                resultItems.onSuccess {
                    initRecyclerView(it!!.toList())
                }

                resultItems.onFailure {
                    initRecyclerView(emptyList())
                }
            }
        }
    }

    private fun showTitle(news: NewsDataUI) {
        val intent = Intent(this,MainActivity::class.java).apply{
            putExtra("id",news.id)
        }
        startActivity(intent)
    }
}