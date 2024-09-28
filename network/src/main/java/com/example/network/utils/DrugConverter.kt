package com.example.network.utils

import android.util.Log
import com.example.network.domain.model.ListDrugsItemModel
import com.example.network.domain.model.ListDrugsModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DrugConverter {
    private val gson = Gson()

    fun fromListDrugsItem(listDrugsItem: ListDrugsItemModel): String {
        return gson.toJson(listDrugsItem)
    }

    fun toListDrugsItem(listDrugsItemString: String): ListDrugsItemModel {
        val vocationType = object : TypeToken<ListDrugsItemModel>() {}.type
        return gson.fromJson(listDrugsItemString, vocationType)
    }

    fun fromListDrugs(listDrugs: ListDrugsModel): String {
        return gson.toJson(listDrugs)
    }

    fun toListDrugs(listDrugsString: String): ListDrugsModel {
        val vocationType = object : TypeToken<ListDrugsModel>() {}.type
        return gson.fromJson(listDrugsString, vocationType)
    }
}