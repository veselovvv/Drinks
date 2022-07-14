package com.veselovvv.drinks.di.cocktaildetails

import android.content.Context
import com.veselovvv.drinks.core.Read
import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsDataToDomainMapper
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsRepository
import com.veselovvv.drinks.data.cocktaildetails.CocktailsDetailsDataToDomainMapper
import com.veselovvv.drinks.domain.cocktaildetails.*
import com.veselovvv.drinks.presentation.cocktaildetails.BaseCocktailDetailsDomainToUiMapper
import com.veselovvv.drinks.presentation.cocktaildetails.BaseCocktailsDetailsDomainToUiMapper
import com.veselovvv.drinks.presentation.cocktaildetails.CocktailsDetailsCommunication
import com.veselovvv.drinks.presentation.cocktails.CocktailCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class CocktailDetailsDomainModule {
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
}