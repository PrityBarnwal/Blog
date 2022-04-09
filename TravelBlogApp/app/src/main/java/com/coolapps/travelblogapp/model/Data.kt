package com.coolapps.travelblogapp.model

data class Data(
    val author: Author,
    val date: String,
    val description: String,
    val image: String,
    val rating: Double,
    val title: String,
    val views: Int,
    val id: String
)
