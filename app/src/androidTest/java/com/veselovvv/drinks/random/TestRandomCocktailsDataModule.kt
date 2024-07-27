package com.veselovvv.drinks.random

import com.veselovvv.drinks.data.randomcocktail.RandomCocktailRepository
import com.veselovvv.drinks.di.randomcocktail.RandomCocktailDataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RandomCocktailDataModule::class] // replaces RandomCocktailDataModule with this fake one
)
class TestRandomCocktailsDataModule {
    @Provides
    @Singleton
    fun provideRandomCocktailsRepository(): RandomCocktailRepository =
        TestRandomCocktailsRepository()
}