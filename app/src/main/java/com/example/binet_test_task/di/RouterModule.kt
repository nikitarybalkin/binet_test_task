package com.example.binet_test_task.di

import com.example.binet_test_task.ListRouterImpl
import dagger.Module
import dagger.Provides
import features.list.presentation.ListRouter

@Module
class RouterModule {

    @Provides
    fun providesListRouter(listRouterImpl: ListRouterImpl): ListRouter {
        return listRouterImpl
    }

}