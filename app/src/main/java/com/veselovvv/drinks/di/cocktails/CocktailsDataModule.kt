package com.veselovvv.drinks.di.cocktails

import android.content.Context
import com.google.gson.Gson
import com.veselovvv.drinks.data.cocktails.CocktailsRepository
import com.veselovvv.drinks.data.cocktails.ToCocktailMapper
import com.veselovvv.drinks.data.cocktails.cache.CocktailDataToDbMapper
import com.veselovvv.drinks.data.cocktails.cache.CocktailsCacheDataSource
import com.veselovvv.drinks.data.cocktails.cache.CocktailsCacheMapper
import com.veselovvv.drinks.data.cocktails.cloud.CocktailsCloudDataSource
import com.veselovvv.drinks.data.cocktails.cloud.CocktailsCloudMapper
import com.veselovvv.drinks.data.cocktails.cloud.CocktailsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CocktailsDataModule {
    @Provides
    @Singleton
    fun provideCocktailsService(retrofit: Retrofit): CocktailsService =
        retrofit.create(CocktailsService::class.java)

    @Provides
    @Singleton
    fun provideCocktailsCloudDataSource(service: CocktailsService, gson: Gson): CocktailsCloudDataSource =
        CocktailsCloudDataSource.Base(service, gson)

    @Provides
    @Singleton
    fun provideCocktailDataToDbMapper(): CocktailDataToDbMapper = CocktailDataToDbMapper.Base()

    @Provides
    @Singleton
    fun provideCocktailsCacheDataSource(
        @ApplicationContext context: Context,
        mapper: CocktailDataToDbMapper
    ): CocktailsCacheDataSource = CocktailsCacheDataSource.Base(context, mapper)

    @Provides
    @Singleton
    fun provideToCocktailMapper(): ToCocktailMapper = ToCocktailMapper.Base()

    @Provides
    @Singleton
    fun provideCocktailsCloudMapper(toCocktailMapper: ToCocktailMapper): CocktailsCloudMapper =
        CocktailsCloudMapper.Base(toCocktailMapper)

    @Provides
    @Singleton
    fun provideCocktailsCacheMapper(toCocktailMapper: ToCocktailMapper): CocktailsCacheMapper =
        CocktailsCacheMapper.Base(toCocktailMapper)

    @Provides
    @Singleton
    fun provideCocktailsRepository(
        cloudDataSource: CocktailsCloudDataSource,
        cacheDataSource: CocktailsCacheDataSource,
        cocktailsCloudMapper: CocktailsCloudMapper,
        cocktailsCacheMapper: CocktailsCacheMapper
    ): CocktailsRepository = CocktailsRepository.Base(
        cloudDataSource, cacheDataSource, cocktailsCloudMapper, cocktailsCacheMapper
    )
}