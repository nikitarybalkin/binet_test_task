package com.example.network.domain

import androidx.paging.PagingData
import com.example.network.domain.model.ListDrugsItemModel
import com.example.network.domain.model.ListDrugsModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DrugsRemoteUseCase @Inject constructor(private val drugsRemoteRepository: DrugsRemoteRepository) {
    suspend fun getDrugsPaging(): Flow<PagingData<ListDrugsItemModel>> {
        return drugsRemoteRepository.getDrugsPaging()
    }

    suspend fun getDrugBySearch(name: String): ListDrugsModel {
        return drugsRemoteRepository.getDrugBySearch(name)
    }
}