package features.list.di

import dagger.Subcomponent
import features.list.presentation.ListFragment
import features.list.presentation.OneItemFragment
import features.list.presentation.SearchFragment

@Subcomponent
interface ListComponent {

    fun inject(mainFragment: ListFragment)
    fun inject(oneItemFragment: OneItemFragment)
    fun inject(searchFragment: SearchFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ListComponent
    }
}