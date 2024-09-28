package com.example.binet_test_task.di

import android.content.Context
import com.example.network.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import features.list.di.ListComponent

@Component(modules = [NetworkModule::class, ViewModelModule::class, RouterModule::class])
interface ApplicationComponent {
    fun listComponent(): ListComponent.Factory
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}