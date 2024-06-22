package com.altamirano.myfirstapp.data.network.entities.marvel

data class Series(
    val available: String,
    val collectionURI: String,
    val items: List<Item>,
    val returned: String
)