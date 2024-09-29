package com.example.binet_test_task.di

import android.content.Context
import com.example.network.di.NetworkModule
import com.example.search.di.SearchComponent
import dagger.BindsInstance
import dagger.Component
import features.list.di.ListComponent

@Component(modules = [NetworkModule::class, ViewModelModule::class, RouterModule::class])
interface ApplicationComponent {
    fun listComponent(): ListComponent.Factory
    fun searchComponent(): SearchComponent.Factory
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}