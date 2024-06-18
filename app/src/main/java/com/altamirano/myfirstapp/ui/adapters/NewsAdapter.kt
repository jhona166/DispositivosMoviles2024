package com.altamirano.myfirstapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load


import com.altamirano.myfirstapp.R
import com.altamirano.myfirstapp.data.network.entities.NewsDataUI

import com.altamirano.myfirstapp.databinding.ItemTopNewsBinding

class NewsAdapter(
   // private val itemList: List<NewsDataUI>
            private val onClickItem:(NewsDataUI)->Unit,
            private val onDeleteItem:(Int)->Unit,
            private val onInsertItem:()->Unit
) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    var itemList: List<NewsDataUI> = emptyList()


    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemTopNewsBinding.bind(view)

        fun render(data: NewsDataUI,
                   onClickItem:(NewsDataUI)->Unit,
                   onDeleteItem:(Int)->Unit,
                   onInsertItem:()->Unit
        )

            {

            binding.txtTitleNews.text = data.name
            binding.txtUrlNews.text = data.url
            binding.txtDescNews.text = data.description
            binding.txtLanguage.text =   data.language


          binding.imgNews
               .load(data.image) {
                   placeholder(R.drawable.logo)
               }

            itemView.setOnClickListener{
                  onClickItem(data)
            }

            binding.btnDelete.setOnClickListener{
                onDeleteItem(adapterPosition)

            }
            binding.btnInsert.setOnClickListener{
                onInsertItem()
            }

        }
    }
//1-06-52
    //27/05/24
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(
            inflater.inflate(
                R.layout.item_top_news,
                parent,
                false
            )
        )

    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.render(itemList[position],
                     onClickItem,
                      onDeleteItem,
                      onInsertItem
        )
    }


}