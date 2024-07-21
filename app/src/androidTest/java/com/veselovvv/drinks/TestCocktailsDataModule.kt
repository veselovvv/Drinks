package com.veselovvv.drinks

import com.veselovvv.drinks.data.cocktails.CocktailsRepository
import com.veselovvv.drinks.di.cocktails.CocktailsDataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CocktailsDataModule::class] // replaces CocktailsDataModule with this fake one
)
class TestCocktailsDataModule {
    @Provides
    @Singleton
    fun provideCocktailsRepository(): CocktailsRepository = TestCocktailsRepository()
}