package com.veselovvv.drinks.categories

import com.veselovvv.drinks.core.Category
import com.veselovvv.drinks.data.subcategories.SubcategoriesData
import com.veselovvv.drinks.data.subcategories.SubcategoriesRepository
import com.veselovvv.drinks.data.subcategories.SubcategoryData
import java.net.UnknownHostException

class TestSubcategoriesRepository : SubcategoriesRepository {
    private var isSuccess = false

    override suspend fun fetchSubcategories(category: Category): SubcategoriesData {
        isSuccess = !isSuccess

        return if (isSuccess) SubcategoriesData.Success(
            when (category) {
                Category.CATEGORIES -> listOf(
                    SubcategoryData("Ordinary Drink"),
                    SubcategoryData("Cocktail"),
                    SubcategoryData("Shake"),
                    SubcategoryData("Coffee / Tea")
                )
                Category.GLASS -> listOf(
                    SubcategoryData("Cocktail glass"),
                    SubcategoryData("Whiskey glass"),
                    SubcategoryData("Wine glass")
                )
                Category.INGREDIENTS -> listOf(
                    SubcategoryData("Gin"),
                    SubcategoryData("Dark rum"),
                    SubcategoryData("Sugar"),
                    SubcategoryData("Milk")
                )
                Category.ALCOHOLIC -> listOf(
                    SubcategoryData("Alcoholic"),
                    SubcategoryData("Non alcoholic"),
                    SubcategoryData("Optional alcohol")
                )
            }
        ) else SubcategoriesData.Fail(UnknownHostException())
    }
}