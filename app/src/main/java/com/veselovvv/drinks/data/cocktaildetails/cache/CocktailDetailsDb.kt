package com.veselovvv.drinks.data.cocktaildetails.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsData
import com.veselovvv.drinks.data.cocktaildetails.ToCocktailDetailsMapper

@Entity
data class CocktailDetailsDb(
    @PrimaryKey var name: String = "",
    var alcoholic: String = "",
    var glass: String = "",
    var instructions: String = "",
    var ingredient1: String = "",
    var ingredient2: String = "",
    var ingredient3: String = "",
    var ingredient4: String = "",
    var ingredient5: String = "",
    var ingredient6: String = "",
    var ingredient7: String = "",
    var ingredient8: String = "",
    var ingredient9: String = "",
    var ingredient10: String = ""
) : Object<CocktailDetailsData, ToCocktailDetailsMapper> {
    override fun map(mapper: ToCocktailDetailsMapper) = mapper.map(
        name, alcoholic, glass, instructions, listOf(
            ingredient1, ingredient2, ingredient3, ingredient4, ingredient5,
            ingredient6, ingredient7, ingredient8, ingredient9, ingredient10
        )
    )
}
