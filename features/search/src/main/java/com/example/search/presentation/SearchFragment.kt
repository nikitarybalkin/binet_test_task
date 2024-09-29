package com.example.search.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.core.ViewModelFactory
import com.example.network.utils.DrugConverter
import com.example.search.databinding.FragmentSearchBinding
import com.example.search.di.SearchComponent
import com.example.search.di.SearchComponentProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : Fragment() {
    @Inject
    lateinit var listRouter: SearchRouter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var listComponent: SearchComponent
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private val fragment = this

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listComponent =
            (requireActivity().applicationContext as SearchComponentProvider).provideSearchComponent()
        listComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchIsGood.collect {
                it?.let {
                    if (it.listDrugs.isNotEmpty()) {
                        listRouter.goToOneItemFromSearchFragment(fragment, DrugConverter().fromListDrugs(it))
                    }
                }
            }
        }
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.name = s.toString()
            }
        })
        binding.ivSearch.setOnClickListener {
            if (viewModel.name != "") {
                viewModel.getDrugBySearch()
            } else Toast.makeText(requireActivity().applicationContext, resources.getString(com.example.core.R.string.field_must_not), Toast.LENGTH_SHORT).show()
        }
        binding.ivBack.setOnClickListener {

            listRouter.goToListFragmentFromSearch(this)

        }
    }


}