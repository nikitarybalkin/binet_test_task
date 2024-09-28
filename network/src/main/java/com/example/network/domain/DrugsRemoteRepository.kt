package com.example.network.domain

import androidx.paging.PagingData
import com.example.network.domain.model.ListDrugsItemModel
import com.example.network.domain.model.ListDrugsModel
import kotlinx.coroutines.flow.Flow

interface DrugsRemoteRepository {
    suspend fun getDrugsPaging(): Flow<PagingData<ListDrugsItemModel>>
    suspend fun getDrugBySearch(name: String): ListDrugsModel
}