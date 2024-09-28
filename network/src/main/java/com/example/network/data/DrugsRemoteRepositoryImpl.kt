package com.example.network.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.network.domain.DrugsRemoteRepository
import com.example.network.domain.model.ListDrugsItemModel
import com.example.network.domain.model.ListDrugsModel
import com.example.network.domain.model.mapToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DrugsRemoteRepositoryImpl @Inject constructor(private val drugsRemoteDataSource: DrugsRemoteDataSource): DrugsRemoteRepository {
    override suspend fun getDrugsPaging(): Flow<PagingData<ListDrugsItemModel>> {
        return Pager(
            config = PagingConfig(pageSize = 15),
            pagingSourceFactory = {ListPagingSource(drugsRemoteDataSource)}
        ).flow.map { it.map { it.mapToDomain() } }
    }

    override suspend fun getDrugBySearch(name: String): ListDrugsModel {
        return drugsRemoteDataSource.getDrugBySearch(name).mapToDomain()
    }

}