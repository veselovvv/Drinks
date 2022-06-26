package com.veselovvv.drinks.presentation.cocktaildetails

import androidx.lifecycle.ViewModel
import com.veselovvv.drinks.core.Read
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CocktailDetailsViewModel @Inject constructor(
    private val cocktailCache: Read<Triple<String, String, String>>
) : ViewModel() {
    fun getCocktailName() = cocktailCache.read().first
    fun getCocktailCategory() = cocktailCache.read().second
    fun getCocktailPhotoUrl() = cocktailCache.read().third
}