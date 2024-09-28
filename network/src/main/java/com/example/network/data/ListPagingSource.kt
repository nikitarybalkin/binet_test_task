package com.example.network.data

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.network.data.Response.ListDrugsItem
import javax.inject.Inject

class ListPagingSource @Inject constructor(
    private val drugsRemoteDataSource: DrugsRemoteDataSource
) : PagingSource<Int, ListDrugsItem>() {

    @SuppressLint("SuspiciousIndentation")
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListDrugsItem> {
        return try {
            val position = params.key ?: 1
            val response = drugsRemoteDataSource.getListDrugs(position)
                LoadResult.Page(
                    data = response,
                    prevKey = if (position == 1) null else (position - 1),
                    nextKey = if (response.isEmpty()) null else (position + 1)
                )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, ListDrugsItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
