package com.veselovvv.drinks.data.cocktails.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CocktailDao {
    @Query("SELECT * FROM cocktailDb")
    fun getCocktails(): List<CocktailDb>

    @Insert
    fun addCocktails(cocktails: List<CocktailDb>)

    @Query("DELETE FROM cocktailDb")
    fun deleteCocktails()
}