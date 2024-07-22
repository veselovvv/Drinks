package com.veselovvv.drinks

import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsRepository
import com.veselovvv.drinks.di.cocktaildetails.CocktailDetailsDataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CocktailDetailsDataModule::class] // replaces CocktailDetailsDataModule with this fake one
)
class TestCocktailsDetailsDataModule {
    @Provides
    @Singleton
    fun provideCocktailDetailsRepository(): CocktailDetailsRepository =
        TestCocktailsDetailsRepository()
}