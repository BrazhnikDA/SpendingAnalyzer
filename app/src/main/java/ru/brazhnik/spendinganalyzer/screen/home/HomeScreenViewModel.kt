package ru.brazhnik.spendinganalyzer.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUIState> =
        MutableStateFlow(HomeUIState.Loading)

    internal val uiState: StateFlow<HomeUIState> = _uiState

    init {
        getData()
    }

    private fun getData() {
        // call use case
        viewModelScope.launch(dispatcher) {
            _uiState.value = HomeUIState.Success(
                listOf<Item>(
                    Item(
                        "Помидоры",
                        179.58,
                        CategoriesBuy.PRODUCTS
                    ),
                    Item(
                        "Огурцы",
                        229.58,
                        CategoriesBuy.PRODUCTS
                    ),
                )
            )
        }
    }
}
