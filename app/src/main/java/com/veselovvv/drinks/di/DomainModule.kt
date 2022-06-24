package com.veselovvv.drinks.di

import android.content.Context
import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.data.cocktails.CocktailDataToDomainMapper
import com.veselovvv.drinks.data.cocktails.CocktailsDataToDomainMapper
import com.veselovvv.drinks.domain.cocktails.*
import com.veselovvv.drinks.presentation.cocktails.BaseCocktailDomainToUiMapper
import com.veselovvv.drinks.presentation.cocktails.BaseCocktailsDomainToUiMapper
import com.veselovvv.drinks.presentation.cocktails.CocktailsCommunication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideCocktailDataToDomainMapper(): CocktailDataToDomainMapper = BaseCocktailDataToDomainMapper()

    @Provides
    fun provideCocktailsDataToDomainMapper(
        cocktailMapper: CocktailDataToDomainMapper
    ): CocktailsDataToDomainMapper = BaseCocktailsDataToDomainMapper(cocktailMapper)

    @Provides
    fun provideFetchCocktailsUseCase(
        cocktailsRepository: CocktailsRepository, mapper: CocktailsDataToDomainMapper
    ): FetchCocktailsUseCase = FetchCocktailsUseCase(cocktailsRepository, mapper)

    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider =
        ResourceProvider.Base(context)

    @Provides
    fun provideCocktailDomainToUiMapper(): CocktailDomainToUiMapper = BaseCocktailDomainToUiMapper()

    @Provides
    fun provideCocktailsDomainToUiMapper(
        resourceProvider: ResourceProvider,
        cocktailMapper: CocktailDomainToUiMapper
    ): CocktailsDomainToUiMapper = BaseCocktailsDomainToUiMapper(resourceProvider, cocktailMapper)

    @Provides
    fun provideCocktailsCommunication(): CocktailsCommunication = CocktailsCommunication.Base()
}