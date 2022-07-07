package com.veselovvv.drinks.data.cocktails.cache

import android.content.Context
import com.veselovvv.drinks.data.cocktails.CocktailData
import com.veselovvv.drinks.data.core.BaseCacheDataSource

interface CocktailsCacheDataSource {
    fun save(cocktails: List<CocktailData>)
    fun read(): List<CocktailDb>
    fun clear()

    class Base(
        context: Context,
        private val mapper: CocktailDataToDbMapper
    ) : CocktailsCacheDataSource, BaseCacheDataSource.Abstract<CocktailDao>(
        context, "cocktail-database"
    ) {
        override fun dao() = room.cocktailDao()

        override fun save(cocktails: List<CocktailData>) {
            val cocktailsDb = mutableListOf<CocktailDb>()
            cocktails.forEach { cocktailsDb.add(it.mapWith(mapper)) }
            dao().addCocktails(cocktailsDb)
        }

        override fun read() = dao().getCocktails()
        override fun clear() = dao().deleteCocktails()
    }
}