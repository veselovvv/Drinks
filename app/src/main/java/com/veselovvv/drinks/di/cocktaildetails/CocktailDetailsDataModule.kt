package com.veselovvv.drinks.di.cocktaildetails

import android.content.Context
import com.google.gson.Gson
import com.veselovvv.drinks.data.cocktaildetails.CocktailDetailsRepository
import com.veselovvv.drinks.data.cocktaildetails.ToCocktailDetailsMapper
import com.veselovvv.drinks.data.cocktaildetails.cache.CocktailDetailsCacheDataSource
import com.veselovvv.drinks.data.cocktaildetails.cache.CocktailDetailsCacheMapper
import com.veselovvv.drinks.data.cocktaildetails.cache.CocktailDetailsDataToDbMapper
import com.veselovvv.drinks.data.cocktaildetails.cloud.CocktailDetailsCloudDataSource
import com.veselovvv.drinks.data.cocktaildetails.cloud.CocktailDetailsCloudMapper
import com.veselovvv.drinks.data.cocktaildetails.cloud.CocktailDetailsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CocktailDetailsDataModule {
    @Provides
    @Singleton
    fun provideCocktailDetailsService(retrofit: Retrofit) : CocktailDetailsService =
        retrofit.create(CocktailDetailsService::class.java)

    @Provides
    @Singleton
    fun provideCocktailDetailsCloudDataSource(
        service: CocktailDetailsService,
        gson: Gson
    ): CocktailDetailsCloudDataSource = CocktailDetailsCloudDataSource.Base(service, gson)

    @Provides
    @Singleton
    fun provideCocktailDetailsDataToDbMapper(): CocktailDetailsDataToDbMapper =
        CocktailDetailsDataToDbMapper.Base()

    @Provides
    @Singleton
    fun provideCocktailDetailsCacheDataSource(
        @ApplicationContext context: Context,
        mapper: CocktailDetailsDataToDbMapper
    ): CocktailDetailsCacheDataSource = CocktailDetailsCacheDataSource.Base(context, mapper)

    @Provides
    @Singleton
    fun provideToCocktailDetailsMapper(): ToCocktailDetailsMapper = ToCocktailDetailsMapper.Base()

    @Provides
    @Singleton
    fun provideCocktailDetailsCloudMapper(
        toCocktailDetailsMapper: ToCocktailDetailsMapper
    ): CocktailDetailsCloudMapper = CocktailDetailsCloudMapper.Base(toCocktailDetailsMapper)

    @Provides
    @Singleton
    fun provideCocktailDetailsCacheMapper(
        toCocktailDetailsMapper: ToCocktailDetailsMapper
    ): CocktailDetailsCacheMapper = CocktailDetailsCacheMapper.Base(toCocktailDetailsMapper)

    @Provides
    @Singleton
    fun provideCocktailDetailsRepository(
        cloudDataSource: CocktailDetailsCloudDataSource,
        cacheDataSource: CocktailDetailsCacheDataSource,
        cocktailDetailsCloudMapper: CocktailDetailsCloudMapper,
        cocktailDetailsCacheMapper: CocktailDetailsCacheMapper
    ): CocktailDetailsRepository = CocktailDetailsRepository.Base(
        cloudDataSource, cacheDataSource, cocktailDetailsCloudMapper, cocktailDetailsCacheMapper
    )
}