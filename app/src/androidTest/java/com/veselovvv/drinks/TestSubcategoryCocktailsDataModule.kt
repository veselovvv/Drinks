package com.veselovvv.drinks

import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailsRepository
import com.veselovvv.drinks.di.subcategorycocktails.SubcategoryCocktailsDataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [SubcategoryCocktailsDataModule::class] // replaces SubcategoryCocktailsDataModule with this fake one
)
class TestSubcategoryCocktailsDataModule {
    @Provides
    @Singleton
    fun provideSubcategoryCocktailsRepository(): SubcategoryCocktailsRepository =
        TestSubcategoryCocktailsRepository()
}