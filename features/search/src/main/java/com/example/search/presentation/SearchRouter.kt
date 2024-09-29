package com.example.search.presentation

import androidx.fragment.app.Fragment

interface SearchRouter {
    fun goToListFragmentFromSearch(fragment: Fragment)
    fun goToOneItemFromSearchFragment(fragment: Fragment, oneDrug: String)
}