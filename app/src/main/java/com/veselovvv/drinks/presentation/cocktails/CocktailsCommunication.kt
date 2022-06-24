package com.veselovvv.drinks.presentation.cocktails

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.veselovvv.drinks.core.Mapper

interface CocktailsCommunication : Mapper {
    fun map(cocktails: List<CocktailUi>)
    fun observe(owner: LifecycleOwner, observer: Observer<List<CocktailUi>>)

    class Base : CocktailsCommunication {
        private val cocktailsLiveData = MutableLiveData<List<CocktailUi>>()

        override fun map(cocktails: List<CocktailUi>) {
            cocktailsLiveData.value = cocktails
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<CocktailUi>>) =
            cocktailsLiveData.observe(owner, observer)
    }
}