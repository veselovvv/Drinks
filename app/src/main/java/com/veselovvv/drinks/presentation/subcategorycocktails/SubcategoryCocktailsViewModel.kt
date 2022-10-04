package com.veselovvv.drinks.presentation.subcategorycocktails

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.drinks.core.Category
import com.veselovvv.drinks.domain.subcategorycocktails.FetchSubcategoryCocktailsUseCase
import com.veselovvv.drinks.domain.subcategorycocktails.SubcategoryCocktailsDomainToUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SubcategoryCocktailsViewModel @Inject constructor(
    private val fetchSubcategoryCocktailsUseCase: FetchSubcategoryCocktailsUseCase,
    private val mapper: SubcategoryCocktailsDomainToUiMapper,
    private val communication: SubcategoryCocktailsCommunication
) : ViewModel() {
    fun fetchCocktails(category: Category, subcategoryName: String) {
        communication.map(listOf(SubcategoryCocktailUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = fetchSubcategoryCocktailsUseCase.execute(category, subcategoryName)
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<SubcategoryCocktailUi>>) =
        communication.observe(owner, observer)
}