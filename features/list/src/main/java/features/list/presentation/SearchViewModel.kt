package features.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.domain.DrugsRemoteUseCase
import com.example.network.domain.model.ListDrugsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val drugsRemoteUseCase: DrugsRemoteUseCase) : ViewModel() {
    val searchIsGood: MutableStateFlow<ListDrugsModel?> = MutableStateFlow(null)
    var name: String = ""
    fun getDrugBySearch() {
        viewModelScope.launch {
            try {
                drugsRemoteUseCase.getDrugBySearch(name)?.let {
                    searchIsGood.value = it
                }
            } catch (e: Exception) {
                searchIsGood.value = null
            }
        }
    }
}