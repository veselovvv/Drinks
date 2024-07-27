package com.veselovvv.drinks.ingredients

import com.veselovvv.drinks.data.searchingredient.IngredientRepository
import com.veselovvv.drinks.di.searchingredient.SearchIngredientDataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [SearchIngredientDataModule::class] // replaces SearchIngredientDataModule with this fake one
)
class TestIngredientDataModule {
    @Provides
    @Singleton
    fun provideTestIngredientRepository(): IngredientRepository = TestIngredientRepository()
}