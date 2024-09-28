package com.example.network.data

import com.example.network.data.Response.ListDrugs
import retrofit2.http.GET
import retrofit2.http.Query

interface DrugsAPI {
    @GET("/api/ppp/index/")
    suspend fun getListOfDrugs(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20
        ): ListDrugs

    @GET("/api/ppp/index/")
    suspend fun getDrugBySearch(
        @Query("search") name: String
        ): ListDrugs
}