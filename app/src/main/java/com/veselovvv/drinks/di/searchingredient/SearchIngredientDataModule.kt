package com.veselovvv.drinks.di.searchingredient

import com.google.gson.Gson
import com.veselovvv.drinks.data.searchingredient.IngredientRepository
import com.veselovvv.drinks.data.searchingredient.ToIngredientMapper
import com.veselovvv.drinks.data.searchingredient.cloud.IngredientCloudDataSource
import com.veselovvv.drinks.data.searchingredient.cloud.IngredientCloudMapper
import com.veselovvv.drinks.data.searchingredient.cloud.SearchIngredientService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchIngredientDataModule {
    @Provides
    @Singleton
    fun provideSearchIngredientService(retrofit: Retrofit): SearchIngredientService =
        retrofit.create(SearchIngredientService::class.java)

    @Provides
    @Singleton
    fun provideIngredientCloudDataSource(
        service: SearchIngredientService,
        gson: Gson
    ): IngredientCloudDataSource = IngredientCloudDataSource.Base(service, gson)

    @Provides
    @Singleton
    fun provideToIngredientMapper(): ToIngredientMapper = ToIngredientMapper.Base()

    @Provides
    @Singleton
    fun provideIngredientCloudMapper(
        toIngredientMapper: ToIngredientMapper
    ): IngredientCloudMapper = IngredientCloudMapper.Base(toIngredientMapper)

    @Provides
    @Singleton
    fun provideIngredientRepository(
        cloudDataSource: IngredientCloudDataSource,
        cloudMapper: IngredientCloudMapper
    ): IngredientRepository = IngredientRepository.Base(cloudDataSource, cloudMapper)
}