package com.example.binet_test_task.di

import com.example.binet_test_task.ListRouterImpl
import com.example.binet_test_task.SearchRouterImpl
import com.example.search.presentation.SearchRouter
import dagger.Module
import dagger.Provides
import features.list.presentation.ListRouter

@Module
class RouterModule {

    @Provides
    fun providesListRouter(listRouterImpl: ListRouterImpl): ListRouter {
        return listRouterImpl
    }

    @Provides
    fun providesSearchRouter(searchRouterImpl: SearchRouterImpl): SearchRouter {
        return searchRouterImpl
    }

}