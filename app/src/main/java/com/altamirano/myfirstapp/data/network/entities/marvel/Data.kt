package com.altamirano.myfirstapp.data.network.entities.marvel

data class Data(
    val count: String,
    val limit: String,
    val offset: String,
    val results: List<Result>,
    val total: String
)