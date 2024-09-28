package com.example.network.data

import android.util.Log
import com.example.network.data.Response.ListDrugs
import javax.inject.Inject

class DrugsRemoteDataSourceImpl @Inject constructor(private val drugsAPI: DrugsAPI): DrugsRemoteDataSource {
    override suspend fun getListDrugs(offset: Int): ListDrugs {
        return drugsAPI.getListOfDrugs(offset = offset)
    }

    override suspend fun getDrugBySearch(name: String): ListDrugs {
        return drugsAPI.getDrugBySearch(name)
    }
}