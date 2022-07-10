package com.veselovvv.drinks.presentation.randomcocktail

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.veselovvv.drinks.core.Mapper

interface RandomCocktailCommunication : Mapper {
    fun map(randomCocktail: RandomCocktailUi)
    fun observe(owner: LifecycleOwner, observer: Observer<RandomCocktailUi>)

    class Base : RandomCocktailCommunication {
        private val randomCocktailLiveData = MutableLiveData<RandomCocktailUi>()

        override fun map(randomCocktail: RandomCocktailUi) {
            randomCocktailLiveData.value = randomCocktail
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<RandomCocktailUi>) =
            randomCocktailLiveData.observe(owner, observer)
    }
}