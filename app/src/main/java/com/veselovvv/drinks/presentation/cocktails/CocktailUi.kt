package com.veselovvv.drinks.presentation.cocktails

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.core.Object

sealed class CocktailUi : Object<Unit, CocktailUi.BaseMapper> {
    override fun map(mapper: BaseMapper) = Unit
    open fun open(cocktailListener: DrinksAdapter.CocktailListener) = Unit

    object Progress : CocktailUi()

    class Base(
        private val id: String,
        private val name: String,
        private val category: String,
        private val photoUrl: String
    ) : CocktailUi() {
        override fun map(mapper: BaseMapper) = mapper.map(id, name, category, photoUrl)
        override fun open(cocktailListener: DrinksAdapter.CocktailListener) =
            cocktailListener.showCocktails(name, category, photoUrl)
    }

    class Fail(private val message: String) : CocktailUi() {
        override fun map(mapper: BaseMapper) = mapper.map(message)
    }

    interface BaseMapper : Mapper {
        fun map(id: String, name: String, category: String, photoUrl: String)
        fun map(text: String)
    }
}