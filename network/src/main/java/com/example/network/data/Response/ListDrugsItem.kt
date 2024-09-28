package com.example.network.data.Response

data class ListDrugsItem(
    val categories: Categories,
    val description: String,
    val documentation: String,
    val fields: List<Field>,
    val gtin: String,
    val id: Int,
    val image: String,
    val name: String
)