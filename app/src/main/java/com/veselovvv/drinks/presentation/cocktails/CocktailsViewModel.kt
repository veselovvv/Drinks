package com.veselovvv.drinks.presentation.cocktails

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.drinks.domain.cocktails.CocktailsDomainToUiMapper
import com.veselovvv.drinks.domain.cocktails.FetchCocktailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CocktailsViewModel @Inject constructor(
    private val fetchCocktailsUseCase: FetchCocktailsUseCase,
    private val mapper: CocktailsDomainToUiMapper,
    private val communication: CocktailsCommunication
) : ViewModel() {
    fun fetchCocktails() {
        communication.map(listOf(CocktailUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = fetchCocktailsUseCase.execute()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<CocktailUi>>) =
        communication.observe(owner, observer)
}