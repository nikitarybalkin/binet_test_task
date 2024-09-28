package com.example.network.domain.model

import com.example.network.data.Response.ListDrugs
import com.example.network.data.Response.ListDrugsItem

class  ListDrugsModel(
    val listDrugs: ArrayList<ListDrugsItem>
)

fun ListDrugs.mapToDomain() = ListDrugsModel(
        this
        )


