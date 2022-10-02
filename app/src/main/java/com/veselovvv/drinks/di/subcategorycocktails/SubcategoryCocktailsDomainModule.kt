package com.veselovvv.drinks.di.subcategorycocktails

import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailDataToDomainMapper
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailsDataToDomainMapper
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailsRepository
import com.veselovvv.drinks.domain.subcategorycocktails.*
import com.veselovvv.drinks.presentation.subcategorycocktails.BaseSubcategoryCocktailDomainToUiMapper
import com.veselovvv.drinks.presentation.subcategorycocktails.BaseSubcategoryCocktailsDomainToUiMapper
import com.veselovvv.drinks.presentation.subcategorycocktails.SubcategoryCocktailsCommunication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class SubcategoryCocktailsDomainModule {
    @Provides
    fun provideSubcategoryCocktailDataToDomainMapper(): SubcategoryCocktailDataToDomainMapper =
        BaseSubcategoryCocktailDataToDomainMapper()

    @Provides
    fun provideSubcategoryCocktailsDataToDomainMapper(
        subcategoryCocktailMapper: SubcategoryCocktailDataToDomainMapper
    ): SubcategoryCocktailsDataToDomainMapper =
        BaseSubcategoryCocktailsDataToDomainMapper(subcategoryCocktailMapper)

    @Provides
    fun provideFetchSubcategoryCocktailsUseCase(
        subcategoryCocktailsRepository: SubcategoryCocktailsRepository,
        mapper: SubcategoryCocktailsDataToDomainMapper
    ): FetchSubcategoryCocktailsUseCase =
        FetchSubcategoryCocktailsUseCase(subcategoryCocktailsRepository, mapper)

    @Provides
    fun provideSubcategoryCocktailDomainToUiMapper(): SubcategoryCocktailDomainToUiMapper =
        BaseSubcategoryCocktailDomainToUiMapper()

    @Provides
    fun provideSubcategoryCocktailsDomainToUiMapper(
        resourceProvider: ResourceProvider,
        subcategoryCocktailMapper: SubcategoryCocktailDomainToUiMapper
    ): SubcategoryCocktailsDomainToUiMapper =
        BaseSubcategoryCocktailsDomainToUiMapper(resourceProvider, subcategoryCocktailMapper)

    @Provides
    fun provideSubcategoryCocktailsCommunication(): SubcategoryCocktailsCommunication =
        SubcategoryCocktailsCommunication.Base()
}