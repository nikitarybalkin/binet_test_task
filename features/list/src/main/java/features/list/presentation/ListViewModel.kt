package features.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.network.domain.DrugsRemoteUseCase
import com.example.network.domain.model.ListDrugsItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(private val drugsRemoteUseCase: DrugsRemoteUseCase) : ViewModel() {
    var pagingDataDrugs: Flow<PagingData<ListDrugsItemModel>?> = MutableStateFlow(null)
    init {
        viewModelScope.launch {
            pagingDataDrugs = drugsRemoteUseCase.getDrugsPaging()
        }
    }

}