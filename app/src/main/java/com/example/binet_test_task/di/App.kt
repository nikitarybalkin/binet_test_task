package com.example.binet_test_task.di

import android.app.Application
import features.list.di.ListComponent
import features.list.di.ListComponentProvider

class App: Application(), ListComponentProvider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun provideListComponent(): ListComponent {
        return component.listComponent().create()
    }

}