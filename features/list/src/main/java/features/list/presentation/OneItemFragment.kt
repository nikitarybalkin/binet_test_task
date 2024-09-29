package features.list.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.core.Constants
import com.example.core.ViewModelFactory
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
        drug = arguments?.getString(Constants().keyForBundleListDrugsItem)?.let { DrugConverter().toListDrugsItem(it) }
        if (drug == null) drug = arguments?.getString(Constants().keyForBundleListDrugs)
            ?.let { DrugConverter().toListDrugs(it).listDrugs[0].mapToDomain() }
        drug?.let {
            drawScreen(it)
        }
    }

    private fun drawScreen(drug: ListDrugsItemModel) {
        binding.tvNameDrug.text = drug.name
        binding.ivBack.setOnClickListener {
            listRouter.goToListFragment(fragment = this)
        }
        binding.tvDescription.text = drug.description
        context?.loadImage(view = binding.ivImageDrug, link = "${Constants().linkToServer}/${drug.image}")
        context?.loadImage(view = binding.ivIconDrug, link = "${Constants().linkToServer}/${drug.categories.icon}")
    }

    private fun Context.loadImage(view: ImageView, link: String) {
        Glide
            .with(this)
            .load(link)
            .into(view)
    }


}