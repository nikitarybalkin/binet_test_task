package com.example.binet_test_task

import android.app.Application
import com.example.binet_test_task.di.DaggerApplicationComponent
import com.example.search.di.SearchComponent
import com.example.search.di.SearchComponentProvider
import features.list.di.ListComponent
import features.list.di.ListComponentProvider

class App: Application(), ListComponentProvider, SearchComponentProvider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun provideListComponent(): ListComponent {
        return component.listComponent().create()
    }

    override fun provideSearchComponent(): SearchComponent {
        return component.searchComponent().create()
    }

}