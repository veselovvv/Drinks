package com.veselovvv.drinks.data.cocktails

data class CocktailData(
    private val id: String,
    private val name: String,
    private val category: String,
    private val photoUrl: String
) {
    fun map(mapper: CocktailDataToDomainMapper) = mapper.map(id, name, category, photoUrl)
}
