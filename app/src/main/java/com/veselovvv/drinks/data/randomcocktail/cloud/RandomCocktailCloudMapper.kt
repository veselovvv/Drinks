package com.veselovvv.drinks.data.randomcocktail.cloud

import com.veselovvv.drinks.core.Mapper
import com.veselovvv.drinks.data.randomcocktail.RandomCocktailData
import com.veselovvv.drinks.data.randomcocktail.ToRandomCocktailMapper

interface RandomCocktailCloudMapper : Mapper.Data<RandomCocktailCloud, RandomCocktailData> {
    class Base(private val toRandomCocktailMapper: ToRandomCocktailMapper) : RandomCocktailCloudMapper {
        override fun map(data: RandomCocktailCloud) = data.map(toRandomCocktailMapper)
    }
}