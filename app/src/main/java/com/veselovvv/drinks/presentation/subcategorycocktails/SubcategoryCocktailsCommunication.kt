package com.veselovvv.drinks.presentation.subcategorycocktails

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.veselovvv.drinks.core.Mapper

interface SubcategoryCocktailsCommunication : Mapper {
    fun map(cocktails: List<SubcategoryCocktailUi>)
    fun observe(owner: LifecycleOwner, observer: Observer<List<SubcategoryCocktailUi>>)

    class Base : SubcategoryCocktailsCommunication {
        private val cocktailsLiveData = MutableLiveData<List<SubcategoryCocktailUi>>()

        override fun map(cocktails: List<SubcategoryCocktailUi>) {
            cocktailsLiveData.value = cocktails
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<SubcategoryCocktailUi>>) {
            cocktailsLiveData.observe(owner, observer)
        }
    }
}