package com.example.network.data

import com.example.network.data.Response.ListDrugs

interface DrugsRemoteDataSource {
    suspend fun getListDrugs(offset: Int): ListDrugs
    suspend fun getDrugBySearch(name: String): ListDrugs
}