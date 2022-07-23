package com.veselovvv.drinks.presentation.searchingredient

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.veselovvv.drinks.core.Mapper

interface IngredientCommunication : Mapper {
    fun map(ingredient: IngredientUi)
    fun observe(owner: LifecycleOwner, observer: Observer<IngredientUi>)

    class Base : IngredientCommunication {
        private val ingredientLiveData = MutableLiveData<IngredientUi>()

        override fun map(ingredient: IngredientUi) {
            ingredientLiveData.value = ingredient
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<IngredientUi>) {
            ingredientLiveData.observe(owner, observer)
        }
    }
}