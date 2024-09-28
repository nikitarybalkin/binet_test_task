package com.example.binet_test_task.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import features.list.presentation.ListViewModel
import features.list.presentation.SearchViewModel

@Module
interface ViewModelModule {

    @IntoMap
    @StringKey("ListViewModel")
    @Binds
    fun bindsListViewModel(impl: ListViewModel): ViewModel

    @IntoMap
    @StringKey("SearchViewModel")
    @Binds
    fun bindsSearchViewModel(impl: SearchViewModel): ViewModel

}