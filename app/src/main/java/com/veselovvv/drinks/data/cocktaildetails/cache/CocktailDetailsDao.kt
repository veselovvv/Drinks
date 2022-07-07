package com.veselovvv.drinks.data.cocktaildetails.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CocktailDetailsDao {
    @Query("SELECT * FROM cocktailDetailsDb WHERE name = :name")
    fun getCocktailDetails(name: String): CocktailDetailsDb

    @Insert
    fun addCocktailDetails(cocktailDetails: CocktailDetailsDb)

    @Query("DELETE FROM cocktailDetailsDb WHERE name = :name")
    fun deleteCocktailDetails(name: String)
}