package com.example.network.domain.model

import com.example.network.data.Response.Categories
import com.example.network.data.Response.Field
import com.example.network.data.Response.ListDrugsItem


data class ListDrugsItemModel (
    val categories: Categories,
    val description: String,
    val documentation: String,
    val fields: List<Field>,
    val gtin: String,
    val id: Int,
    val image: String,
    val name: String
) {

}

fun ListDrugsItem.mapToDomain() = ListDrugsItemModel(
    categories,
    description,
    documentation,
    fields,
    gtin,
    id,
    image,
    name
)

