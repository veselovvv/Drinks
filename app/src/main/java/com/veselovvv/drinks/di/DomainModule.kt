package com.veselovvv.drinks.di

import android.content.Context
import com.veselovvv.drinks.core.Read
import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.core.Save
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsDataToDomainMapper
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsRepository
import com.veselovvv.drinks.data.cocktaildetails.CocktailsDetailsDataToDomainMapper
import com.veselovvv.drinks.data.cocktails.CocktailDataToDomainMapper
import com.veselovvv.drinks.data.cocktails.CocktailsDataToDomainMapper
import com.veselovvv.drinks.data.cocktails.CocktailsRepository
import com.veselovvv.drinks.data.randomcocktail.RandomCocktailDataToDomainMapper
import com.veselovvv.drinks.data.randomcocktail.RandomCocktailRepository
import com.veselovvv.drinks.data.randomcocktail.RandomCocktailsDataToDomainMapper
import com.veselovvv.drinks.domain.cocktaildetails.*
import com.veselovvv.drinks.domain.cocktails.*
import com.veselovvv.drinks.domain.randomcocktail.*
import com.veselovvv.drinks.presentation.cocktaildetails.BaseCocktailDetailsDomainToUiMapper
import com.veselovvv.drinks.presentation.cocktaildetails.BaseCocktailsDetailsDomainToUiMapper
import com.veselovvv.drinks.presentation.cocktaildetails.CocktailsDetailsCommunication
import com.veselovvv.drinks.presentation.cocktails.BaseCocktailDomainToUiMapper
import com.veselovvv.drinks.presentation.cocktails.BaseCocktailsDomainToUiMapper
import com.veselovvv.drinks.presentation.cocktails.CocktailCache
import com.veselovvv.drinks.presentation.cocktails.CocktailsCommunication
import com.veselovvv.drinks.presentation.randomcocktail.BaseRandomCocktailDomainToUiMapper
import com.veselovvv.drinks.presentation.randomcocktail.BaseRandomCocktailsDomainToUiMapper
import com.veselovvv.drinks.presentation.randomcocktail.RandomCocktailCommunication
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
    fun provideFetchCocktailsFromNetworkUseCase(
        cocktailsRepository: CocktailsRepository, mapper: CocktailsDataToDomainMapper
    ): FetchCocktailsFromNetworkUseCase = FetchCocktailsFromNetworkUseCase(cocktailsRepository, mapper)

    @Provides
    fun provideSearchCocktailsUseCase(
        cocktailsRepository: CocktailsRepository, mapper: CocktailsDataToDomainMapper
    ): SearchCocktailsUseCase = SearchCocktailsUseCase(cocktailsRepository, mapper)

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

    @Provides
    fun provideSaveCocktailCache(
        @ApplicationContext context: Context
    ): Save<Triple<String, String, String>> = CocktailCache.Base(context)

    @Provides
    fun provideCocktailDetailsDataToDomainMapper(): CocktailDetailsDataToDomainMapper =
        BaseCocktailDetailsDataToDomainMapper()

    @Provides
    fun provideCocktailsDetailsDataToDomainMapper(
        cocktailDetailsMapper: CocktailDetailsDataToDomainMapper
    ): CocktailsDetailsDataToDomainMapper = BaseCocktailsDetailsDataToDomainMapper(cocktailDetailsMapper)

    @Provides
    fun provideFetchCocktailDetailsUseCase(
        cocktailDetailsRepository: CocktailDetailsRepository,
        cocktailsDetailsDataToDomainMapper: CocktailsDetailsDataToDomainMapper
    ): FetchCocktailDetailsUseCase = FetchCocktailDetailsUseCase(
        cocktailDetailsRepository, cocktailsDetailsDataToDomainMapper
    )

    @Provides
    fun provideFetchCocktailDetailsFromNetworkUseCase(
        cocktailDetailsRepository: CocktailDetailsRepository,
        cocktailsDetailsDataToDomainMapper: CocktailsDetailsDataToDomainMapper
    ): FetchCocktailDetailsFromNetworkUseCase = FetchCocktailDetailsFromNetworkUseCase(
        cocktailDetailsRepository, cocktailsDetailsDataToDomainMapper
    )

    @Provides
    fun provideCocktailDetailsDomainToUiMapper(): CocktailDetailsDomainToUiMapper =
        BaseCocktailDetailsDomainToUiMapper()

    @Provides
    fun provideCocktailsDetailsDomainToUiMapper(
        resourceProvider: ResourceProvider,
        cocktailDetailsMapper: CocktailDetailsDomainToUiMapper
    ): CocktailsDetailsDomainToUiMapper = BaseCocktailsDetailsDomainToUiMapper(
        resourceProvider, cocktailDetailsMapper
    )

    @Provides
    fun provideCocktailsDetailsCommunication(): CocktailsDetailsCommunication =
        CocktailsDetailsCommunication.Base()

    @Provides
    fun provideReadCocktailCache(
        @ApplicationContext context: Context
    ): Read<Triple<String, String, String>> = CocktailCache.Base(context)

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