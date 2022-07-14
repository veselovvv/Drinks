package com.veselovvv.drinks.di.randomcocktail

import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.data.randomcocktail.RandomCocktailDataToDomainMapper
import com.veselovvv.drinks.data.randomcocktail.RandomCocktailRepository
import com.veselovvv.drinks.data.randomcocktail.RandomCocktailsDataToDomainMapper
import com.veselovvv.drinks.domain.randomcocktail.*
import com.veselovvv.drinks.presentation.randomcocktail.BaseRandomCocktailDomainToUiMapper
import com.veselovvv.drinks.presentation.randomcocktail.BaseRandomCocktailsDomainToUiMapper
import com.veselovvv.drinks.presentation.randomcocktail.RandomCocktailCommunication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RandomCocktailDomainModule {
    @Provides
    fun provideRandomCocktailDataToDomainMapper(): RandomCocktailDataToDomainMapper =
        BaseRandomCocktailDataToDomainMapper()

    @Provides
    fun provideRandomCocktailsDataToDomainMapper(
        randomCocktailMapper: RandomCocktailDataToDomainMapper
    ): RandomCocktailsDataToDomainMapper = BaseRandomCocktailsDataToDomainMapper(randomCocktailMapper)

    @Provides
    fun provideFetchRandomCocktailUseCase(
        randomCocktailRepository: RandomCocktailRepository,
        randomCocktailsDataToDomainMapper: RandomCocktailsDataToDomainMapper
    ): FetchRandomCocktailUseCase = FetchRandomCocktailUseCase(
        randomCocktailRepository, randomCocktailsDataToDomainMapper
    )

    @Provides
    fun provideRandomCocktailDomainToUiMapper(): RandomCocktailDomainToUiMapper =
        BaseRandomCocktailDomainToUiMapper()

    @Provides
    fun provideRandomCocktailsDomainToUiMapper(
        resourceProvider: ResourceProvider,
        randomCocktailMapper: RandomCocktailDomainToUiMapper
    ): RandomCocktailsDomainToUiMapper = BaseRandomCocktailsDomainToUiMapper(
        resourceProvider, randomCocktailMapper
    )

    @Provides
    fun provideRandomCocktailCommunication(): RandomCocktailCommunication =
        RandomCocktailCommunication.Base()
}