package features.list.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.core.di.ViewModelFactory
import com.example.network.utils.DrugConverter
import com.example.network.domain.model.ListDrugsItemModel
import features.list.databinding.FragmentListBinding
import features.list.di.ListComponent
import features.list.di.ListComponentProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListFragment : Fragment() {

    @Inject
    lateinit var listRouter: ListRouter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var listComponent: ListComponent
    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: ListViewModel
    private var adapter: ListOfDrugsPagingAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listComponent =
            (requireActivity().applicationContext as ListComponentProvider).provideListComponent()
        listComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[ListViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pagingDataDrugs.collect {
                if (it != null) {
                    adapter = ListOfDrugsPagingAdapter({drug -> goToOneItemFragment(drug)})
                    binding.rvQuickFilters.adapter = adapter
                    adapter?.submitData(it)
                }
            }
        }
        binding.ivSearch.setOnClickListener {
            listRouter.goToSearchFragment(this)
        }
    }
    fun goToOneItemFragment(drug: ListDrugsItemModel?) {
        drug?.let {
            listRouter.goToOneItemFragment(this, DrugConverter().fromListDrugsItem(it))
        }
    }
}
