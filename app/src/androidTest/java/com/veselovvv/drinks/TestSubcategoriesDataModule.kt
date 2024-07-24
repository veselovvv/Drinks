package com.veselovvv.drinks

import com.veselovvv.drinks.data.subcategories.SubcategoriesRepository
import com.veselovvv.drinks.di.subcategories.SubcategoriesDataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [SubcategoriesDataModule::class] // replaces SubcategoriesDataModule with this fake one
)
class TestSubcategoriesDataModule {
    @Provides
    @Singleton
    fun provideSubcategoriesRepository(): SubcategoriesRepository = TestSubcategoriesRepository()
}