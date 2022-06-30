package com.veselovvv.drinks.presentation.cocktaildetails

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.veselovvv.drinks.core.Mapper

interface CocktailsDetailsCommunication : Mapper {
    fun map(cocktailDetails: CocktailDetailsUi)
    fun observe(owner: LifecycleOwner, observer: Observer<CocktailDetailsUi>)

    class Base : CocktailsDetailsCommunication {
        private val cocktailsDetailsLiveData = MutableLiveData<CocktailDetailsUi>()

        override fun map(cocktailDetails: CocktailDetailsUi) {
            cocktailsDetailsLiveData.value = cocktailDetails
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<CocktailDetailsUi>) =
            cocktailsDetailsLiveData.observe(owner, observer)
    }
}