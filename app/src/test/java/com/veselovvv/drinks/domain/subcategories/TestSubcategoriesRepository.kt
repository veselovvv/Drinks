package com.veselovvv.drinks.domain.subcategories

import com.veselovvv.drinks.data.subcategories.SubcategoriesData
import com.veselovvv.drinks.data.subcategories.SubcategoriesRepository
import com.veselovvv.drinks.data.subcategories.SubcategoryData

class TestSubcategoriesRepository(private val exception: Exception? = null) : SubcategoriesRepository {
    private val subcategories = listOf(
        SubcategoryData("Ordinary Drink"),
        SubcategoryData("Cocktail")
    )

    override suspend fun fetchSubcategories(categoryKey: String) =
        if (exception == null) SubcategoriesData.Success(subcategories)
        else SubcategoriesData.Fail(exception)
}