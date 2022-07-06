package com.veselovvv.drinks.data.cocktails.cache

import android.content.Context
import androidx.room.Room
import com.veselovvv.drinks.core.Clear
import com.veselovvv.drinks.core.Read
import com.veselovvv.drinks.core.Save
import com.veselovvv.drinks.data.cocktails.CocktailData

interface CocktailsCacheDataSource : Save<List<CocktailData>>, Read<List<CocktailDb>>, Clear {
    fun cocktailDao(): CocktailDao

    abstract class Abstract(
        context: Context,
        databaseName: String,
        private val mapper: CocktailDataToDbMapper
    ) : CocktailsCacheDataSource {
        private val room = Room.databaseBuilder(
            context,
            CocktailDatabase::class.java,
            databaseName
        ).build()

        override fun cocktailDao() = room.cocktailDao()

        override fun save(data: List<CocktailData>) {
            val cocktails = mutableListOf<CocktailDb>()
            data.forEach { cocktails.add(it.mapWith(mapper)) }
            cocktailDao().addCocktails(cocktails)
        }

        override fun read() = cocktailDao().getCocktails()
        override fun clear() = cocktailDao().deleteCocktails()
    }

    class Base(
        context: Context,
        mapper: CocktailDataToDbMapper
    ) : Abstract(context, "cocktail-database", mapper)
}