package com.altamirano.myfirstapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.altamirano.myfirstapp.R
import com.altamirano.myfirstapp.data.network.entities.NewsDataUI
import com.altamirano.myfirstapp.databinding.FragmentListarNewsBinding
import com.altamirano.myfirstapp.logic.usercases.GetAllTopsNewUserCase
import com.altamirano.myfirstapp.ui.activities.DetailItemActivity
import com.altamirano.myfirstapp.ui.adapters.NewsAdapter
import com.altamirano.myfirstapp.ui.modals.ModalBottomSheet
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ListarNews : Fragment() {

    private lateinit var binding: FragmentListarNewsBinding

    private var items:MutableList<NewsDataUI> = mutableListOf()
    private lateinit var newsAdapter: NewsAdapter

    private lateinit var modalBottomSheet: ModalBottomSheet

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      //  binding = FragmentListarNewsBinding.bind(
        //   inflater.inflate(R.layout.fragment_listar_news,container,false)
        //)
        binding = FragmentListarNewsBinding.bind(inflater.inflate(
            R.layout.fragment_listar_news,
            container,
            false)
        )
        // Inflate the layout for this fragment
        return binding.root

    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVaribles()
        
    }


    private fun initVaribles() {
        newsAdapter = NewsAdapter({descriptionItem(it)}, {deleteItem(it)})

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

        binding.rvTopNews.layoutManager =
            LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL,
                false)

        modalBottomSheet=ModalBottomSheet(null)

    }


    private fun initListeners() {
        binding.refreshRV.setOnRefreshListener{
            initData()
            binding.refreshRV.isRefreshing = false
        }
        binding.btnInsert.setOnClickListener{
            addItem()
        }
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

        Log.d("UIID",news.id)
        val intent = Intent(
            requireContext(),
            DetailItemActivity::class.java
        ).apply{
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