package com.veselovvv.drinks.data.cocktails.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.data.cocktails.CocktailData
import com.veselovvv.drinks.data.cocktails.ToCocktailMapper

@Entity
data class CocktailDb(
    @PrimaryKey var id: String = "",
    var name: String = "",
    var category: String = "",
    var photoUrl: String = ""
) : Object<CocktailData, ToCocktailMapper> {
    override fun map(mapper: ToCocktailMapper) = mapper.map(id, name, category, photoUrl)
}
