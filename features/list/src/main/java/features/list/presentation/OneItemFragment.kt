package features.list.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.core.di.ViewModelFactory
import com.example.network.domain.model.ListDrugsItemModel
import com.example.network.domain.model.mapToDomain
import com.example.network.utils.DrugConverter
import features.list.databinding.FragmentOneItemBinding
import features.list.di.ListComponent
import features.list.di.ListComponentProvider
import javax.inject.Inject

class OneItemFragment : Fragment() {

    @Inject
    lateinit var listRouter: ListRouter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var listComponent: ListComponent
    private lateinit var binding: FragmentOneItemBinding
    private lateinit var viewModel: ListViewModel
    private var adapter: ListOfDrugsPagingAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneItemBinding.inflate(inflater, container, false)
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
        var drug: ListDrugsItemModel? = null
        drug = arguments?.getString("listDrugsItem")?.let { DrugConverter().toListDrugsItem(it) }
        if (drug == null) drug = arguments?.getString("listDrugs")
            ?.let { DrugConverter().toListDrugs(it).listDrugs[0].mapToDomain() }
        drug?.let {
            binding.tvNameDrug.text = drug.name
            binding.ivBack.setOnClickListener {
                listRouter.goToListFragment(fragment = this)
            }
            Glide
                .with(this)
                .load("http://shans.d2.i-partner.ru/${drug.image}")
                .into(binding.ivImageDrug)
            Glide
                .with(this)
                .load("http://shans.d2.i-partner.ru/${drug.categories.icon}")
                .into(binding.ivIconDrug)
            binding.tvDescription.text = drug.description
        }
    }


}