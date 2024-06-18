package com.altamirano.myfirstapp.ui.core

import android.view.View
import com.altamirano.myfirstapp.data.network.entities.NewsDataUI
import com.altamirano.myfirstapp.data.network.entities.topNews.Data

class FunctionExtensions
    fun Data.toNewsDataUI(): NewsDataUI =
         NewsDataUI(
            this.uuid,
            this.url,
            this.title,
            this.image_url,
            this.description,
            this.language
        )


