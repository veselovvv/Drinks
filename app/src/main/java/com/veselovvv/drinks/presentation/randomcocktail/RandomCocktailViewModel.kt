package com.veselovvv.drinks.presentation.randomcocktail

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.drinks.domain.randomcocktail.FetchRandomCocktailUseCase
import com.veselovvv.drinks.domain.randomcocktail.RandomCocktailsDomainToUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RandomCocktailViewModel @Inject constructor(
    private val fetchRandomCocktailUseCase: FetchRandomCocktailUseCase,
    private val mapper: RandomCocktailsDomainToUiMapper,
    private val communication: RandomCocktailCommunication
) : ViewModel() {
    fun fetchRandomCocktail() {
        communication.map(RandomCocktailUi.Progress)
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = fetchRandomCocktailUseCase.execute()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<RandomCocktailUi>) =
        communication.observe(owner, observer)
}