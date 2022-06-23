package com.veselovvv.drinks.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.drinks.domain.cocktails.GetCocktailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CocktailsViewModel(private val getCocktailsUseCase: GetCocktailsUseCase) : ViewModel() {
    private val cocktailsMutableLiveData = MutableLiveData<List<CocktailUi>>()
    val cocktailsLiveData: LiveData<CocktailUi> = cocktailsMutableLiveData

    fun getCocktails() {
        cocktailsMutableLiveData.value = listOf(CocktailUi.Progress)
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = getCocktailsUseCase.execute()
        }
    }
}