package com.veselovvv.drinks.data.subcategories.cloud

import com.google.gson.annotations.SerializedName
import com.veselovvv.drinks.core.Object
import com.veselovvv.drinks.data.subcategories.SubcategoryData
import com.veselovvv.drinks.data.subcategories.ToSubcategoryMapper

interface SubcategoryCloud : Object<SubcategoryData, ToSubcategoryMapper>

data class CategorySubcategoryCloud(
    @SerializedName("strCategory")
    private val subcategory: String
) : SubcategoryCloud {
    override fun map(mapper: ToSubcategoryMapper) = mapper.map(subcategory)
}

data class GlassSubcategoryCloud(
    @SerializedName("strGlass")
    private val subcategory: String
) : SubcategoryCloud {
    override fun map(mapper: ToSubcategoryMapper) = mapper.map(subcategory)
}

data class IngredientSubcategoryCloud(
    @SerializedName("strIngredient1")
    private val subcategory: String
) : SubcategoryCloud {
    override fun map(mapper: ToSubcategoryMapper) = mapper.map(subcategory)
}

data class AlcoholicSubcategoryCloud(
    @SerializedName("strAlcoholic")
    private val subcategory: String
) : SubcategoryCloud {
    override fun map(mapper: ToSubcategoryMapper) = mapper.map(subcategory)
}