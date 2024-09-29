package com.example.binet_test_task

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.search.presentation.SearchRouter
import javax.inject.Inject

class SearchRouterImpl @Inject constructor(): SearchRouter {
    override fun goToListFragmentFromSearch(fragment: Fragment) {
        findNavController(fragment).navigate(R.id.action_searchFragment_to_listFragment)
    }

    override fun goToOneItemFromSearchFragment(fragment: Fragment, oneDrug: String) {
        val bundle = Bundle()
        bundle.putString("listDrugs", oneDrug)
        findNavController(fragment).navigate(R.id.action_searchFragment_to_oneItemFragment, bundle)
    }
}