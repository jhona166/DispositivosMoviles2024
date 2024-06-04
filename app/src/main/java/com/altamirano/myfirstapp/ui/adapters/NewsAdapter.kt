package com.altamirano.myfirstapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.altamirano.myfirstapp.R
import com.altamirano.myfirstapp.databinding.ItemTopNewsBinding

class NewsAdapter(
    private val itemList: List<com.altamirano.myfirstapp.data.network.entities.topNews.Data>
) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemTopNewsBinding.bind(view)

        fun render(data: com.altamirano.myfirstapp.data.network.entities.topNews.Data) {

            binding.txtTitleNews.text = data.title
            binding.txtUrlNews.text = data.url
            binding.txtDescNews.text = data.description

            Log.d("TAG", data.image_url)

            binding.imgNews
                .load(data.image_url) {
                    placeholder(R.drawable.logo)
                }
        }
    }

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
        holder.render(itemList[position])
    }


}