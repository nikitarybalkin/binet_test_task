package features.list.presentation

import androidx.fragment.app.Fragment

interface ListRouter {
    fun goToOneItemFragment(fragment: Fragment, oneDrug: String)
    fun goToListFragment(fragment: Fragment)
    fun goToSearchFragment(fragment: Fragment)
}