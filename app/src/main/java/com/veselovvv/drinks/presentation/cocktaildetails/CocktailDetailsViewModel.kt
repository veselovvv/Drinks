package com.veselovvv.drinks.presentation.cocktaildetails

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.drinks.core.Read
import com.veselovvv.drinks.domain.cocktaildetails.CocktailsDetailsDomainToUiMapper
import com.veselovvv.drinks.domain.cocktaildetails.FetchCocktailDetailsFromNetworkUseCase
import com.veselovvv.drinks.domain.cocktaildetails.FetchCocktailDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CocktailDetailsViewModel @Inject constructor(
    private val fetchCocktailDetailsUseCase: FetchCocktailDetailsUseCase,
    private val fetchCocktailDetailsFromNetworkUseCase: FetchCocktailDetailsFromNetworkUseCase,
    private val mapper: CocktailsDetailsDomainToUiMapper,
    private val communication: CocktailsDetailsCommunication,
    private val cocktailCache: Read<Triple<String, String, String>>
) : ViewModel() {
    fun fetchCocktailDetails(cocktailName: String) {
        communication.map(CocktailDetailsUi.Progress)
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = fetchCocktailDetailsUseCase.execute(cocktailName)
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUi.map(communication)
            }
        }
    }

    fun fetchCocktailDetailsFromNetwork(cocktailName: String) {
        communication.map(CocktailDetailsUi.Progress)
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = fetchCocktailDetailsFromNetworkUseCase.execute(cocktailName)
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<CocktailDetailsUi>) =
        communication.observe(owner, observer)

    fun getCocktailName() = cocktailCache.read().first
    fun getCocktailCategory() = cocktailCache.read().second
    fun getCocktailPhotoUrl() = cocktailCache.read().third
}