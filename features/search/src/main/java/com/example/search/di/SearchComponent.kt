package com.example.search.di

import com.example.search.presentation.SearchFragment
import dagger.Subcomponent

@Subcomponent
interface SearchComponent {

    fun inject(searchFragment: SearchFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): SearchComponent
    }
}