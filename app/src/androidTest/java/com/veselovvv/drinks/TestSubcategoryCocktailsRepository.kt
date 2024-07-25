package com.veselovvv.drinks

import com.veselovvv.drinks.core.Category
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailData
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailsData
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailsRepository
import java.net.UnknownHostException

class TestSubcategoryCocktailsRepository : SubcategoryCocktailsRepository {
    private var isSuccess = false

    override suspend fun fetchCocktails(
        category: Category,
        subcategoryName: String
    ): SubcategoryCocktailsData {
        isSuccess = !isSuccess

        return if (isSuccess) SubcategoryCocktailsData.Success(
            when (category) {
                Category.CATEGORIES -> listOf(
                    SubcategoryCocktailData("410 Gone", "https://somephotopath1"),
                    SubcategoryCocktailData("501 Blue", "https://somephotopath2"),
                    SubcategoryCocktailData("A. J.", "https://somephotopath3")
                )
                Category.GLASS -> listOf(
                    SubcategoryCocktailData("A. J.", "https://somephotopath1"),
                    SubcategoryCocktailData("Adam", "https://somephotopath2"),
                    SubcategoryCocktailData("Almeria", "https://somephotopath3")
                )
                Category.INGREDIENTS -> listOf(
                    SubcategoryCocktailData("Ace", "https://somephotopath1"),
                    SubcategoryCocktailData("Casino", "https://somephotopath2"),
                    SubcategoryCocktailData("Derby", "https://somephotopath3")
                )
                Category.ALCOHOLIC -> listOf(
                    SubcategoryCocktailData("3 Wise Men", "https://somephotopath1"),
                    SubcategoryCocktailData("410 Gone", "https://somephotopath2"),
                    SubcategoryCocktailData("501 Blue", "https://somephotopath3")
                )
            }
        ) else SubcategoryCocktailsData.Fail(UnknownHostException())
    }
}