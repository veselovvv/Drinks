package com.veselovvv.drinks.di.subcategorycocktails

import com.google.gson.Gson
import com.veselovvv.drinks.data.subcategorycocktails.SubcategoryCocktailsRepository
import com.veselovvv.drinks.data.subcategorycocktails.ToSubcategoryCocktailMapper
import com.veselovvv.drinks.data.subcategorycocktails.cloud.SubcategoryCocktailsCloudDataSource
import com.veselovvv.drinks.data.subcategorycocktails.cloud.SubcategoryCocktailsCloudMapper
import com.veselovvv.drinks.data.subcategorycocktails.cloud.SubcategoryCocktailsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SubcategoryCocktailsDataModule {
    @Provides
    @Singleton
    fun provideSubcategoryCocktailsService(retrofit: Retrofit): SubcategoryCocktailsService =
        retrofit.create(SubcategoryCocktailsService::class.java)

    @Provides
    @Singleton
    fun provideSubcategoryCocktailsCloudDataSource(
        service: SubcategoryCocktailsService, gson: Gson
    ): SubcategoryCocktailsCloudDataSource =  SubcategoryCocktailsCloudDataSource.Base(service, gson)

    @Provides
    @Singleton
    fun provideToSubcategoryCocktailMapper(): ToSubcategoryCocktailMapper =
        ToSubcategoryCocktailMapper.Base()

    @Provides
    @Singleton
    fun provideSubcategoryCocktailsCloudMapper(
        toSubcategoryCocktailMapper: ToSubcategoryCocktailMapper
    ): SubcategoryCocktailsCloudMapper = SubcategoryCocktailsCloudMapper.Base(toSubcategoryCocktailMapper)

    @Provides
    @Singleton
    fun provideSubcategoryCocktailsRepository(
        cloudDataSource: SubcategoryCocktailsCloudDataSource,
        subcategoryCocktailsCloudMapper: SubcategoryCocktailsCloudMapper
    ): SubcategoryCocktailsRepository = SubcategoryCocktailsRepository.Base(
        cloudDataSource, subcategoryCocktailsCloudMapper
    )
}