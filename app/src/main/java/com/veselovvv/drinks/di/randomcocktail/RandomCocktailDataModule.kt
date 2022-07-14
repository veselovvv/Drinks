package com.veselovvv.drinks.di.randomcocktail

import com.google.gson.Gson
import com.veselovvv.drinks.data.randomcocktail.RandomCocktailRepository
import com.veselovvv.drinks.data.randomcocktail.ToRandomCocktailMapper
import com.veselovvv.drinks.data.randomcocktail.cloud.RandomCocktailCloudDataSource
import com.veselovvv.drinks.data.randomcocktail.cloud.RandomCocktailCloudMapper
import com.veselovvv.drinks.data.randomcocktail.cloud.RandomCocktailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RandomCocktailDataModule {
    @Provides
    @Singleton
    fun provideRandomCocktailService(retrofit: Retrofit): RandomCocktailService =
        retrofit.create(RandomCocktailService::class.java)

    @Provides
    @Singleton
    fun provideRandomCocktailCloudDataSource(
        service: RandomCocktailService,
        gson: Gson
    ): RandomCocktailCloudDataSource = RandomCocktailCloudDataSource.Base(service, gson)

    @Provides
    @Singleton
    fun provideToRandomCocktailMapper(): ToRandomCocktailMapper = ToRandomCocktailMapper.Base()

    @Provides
    @Singleton
    fun provideRandomCocktailCloudMapper(
        toRandomCocktailMapper: ToRandomCocktailMapper
    ): RandomCocktailCloudMapper = RandomCocktailCloudMapper.Base(toRandomCocktailMapper)

    @Provides
    @Singleton
    fun provideRandomCocktailRepository(
        cloudDataSource: RandomCocktailCloudDataSource,
        cloudMapper: RandomCocktailCloudMapper
    ): RandomCocktailRepository = RandomCocktailRepository.Base(cloudDataSource, cloudMapper)
}