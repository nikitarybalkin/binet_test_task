package com.example.binet_test_task

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import features.list.presentation.ListRouter
import javax.inject.Inject

class ListRouterImpl @Inject constructor(): ListRouter {

    override fun goToOneItemFragment(fragment: Fragment, oneDrug: String) {
        val bundle = Bundle()
        bundle.putString("listDrugsItem", oneDrug)
        findNavController(fragment).navigate(R.id.action_listFragment_to_oneItemFragment, bundle)
    }

    override fun goToListFragment(fragment: Fragment) {
        findNavController(fragment).navigate(R.id.action_oneItemFragment_to_listFragment)
    }

    override fun goToSearchFragment(fragment: Fragment) {
        findNavController(fragment).navigate(R.id.action_listFragment_to_searchFragment)
    }

}