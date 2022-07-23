package com.veselovvv.drinks.presentation.searchingredient

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.drinks.domain.searchingredient.FetchIngredientUseCase
import com.veselovvv.drinks.domain.searchingredient.IngredientsDomainToUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class IngredientViewModel @Inject constructor(
    private val fetchIngredientUseCase: FetchIngredientUseCase,
    private val mapper: IngredientsDomainToUiMapper,
    private val communication: IngredientCommunication
) : ViewModel() {
    fun fetchIngredient(name: String) {
        communication.map(IngredientUi.Progress)
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = fetchIngredientUseCase.execute(name)
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<IngredientUi>) =
        communication.observe(owner, observer)
}