package com.veselovvv.drinks.data.subcategories.cloud

import com.veselovvv.drinks.data.subcategories.SubcategoryData
import com.veselovvv.drinks.data.subcategories.ToSubcategoryMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class SubcategoriesCloudMapperTest {
    @Test
    fun test_mapping() {
        val mapper = SubcategoriesCloudMapper.Base(ToSubcategoryMapper.Base())
        var subcategories: List<SubcategoryCloud> = listOf(
            CategorySubcategoryCloud("Ordinary Drink"),
            CategorySubcategoryCloud("Cocktail")
        )
        var expected = listOf(
            SubcategoryData("Ordinary Drink"),
            SubcategoryData("Cocktail")
        )
        var actual = mapper.map(subcategories)
        assertEquals(expected, actual)

        subcategories = listOf(
            GlassSubcategoryCloud("Highball glass"),
            GlassSubcategoryCloud("Cocktail glass")
        )
        expected = listOf(
            SubcategoryData("Highball glass"),
            SubcategoryData("Cocktail glass")
        )
        actual = mapper.map(subcategories)
        assertEquals(expected, actual)

        subcategories = listOf(
            IngredientSubcategoryCloud("Light rum"),
            IngredientSubcategoryCloud("Applejack")
        )
        expected = listOf(
            SubcategoryData("Light rum"),
            SubcategoryData("Applejack")
        )
        actual = mapper.map(subcategories)
        assertEquals(expected, actual)

        subcategories = listOf(
            AlcoholicSubcategoryCloud("Alcoholic"),
            AlcoholicSubcategoryCloud("Non alcoholic")
        )
        expected = listOf(
            SubcategoryData("Alcoholic"),
            SubcategoryData("Non alcoholic")
        )
        actual = mapper.map(subcategories)
        assertEquals(expected, actual)
    }
}