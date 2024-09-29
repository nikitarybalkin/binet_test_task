package features.list.di

import dagger.Subcomponent
import features.list.presentation.ListFragment
import features.list.presentation.OneItemFragment

@Subcomponent
interface ListComponent {

    fun inject(mainFragment: ListFragment)
    fun inject(oneItemFragment: OneItemFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ListComponent
    }
}