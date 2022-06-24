package com.veselovvv.drinks.di

import com.google.gson.Gson
import com.veselovvv.drinks.data.cocktails.BaseCocktailsRepository
import com.veselovvv.drinks.data.cocktails.CocktailDataToDomainMapper
import com.veselovvv.drinks.data.cocktails.CocktailsDataToDomainMapper
import com.veselovvv.drinks.data.cocktails.ToCocktailMapper
import com.veselovvv.drinks.data.cocktails.cloud.CocktailsCloudDataSource
import com.veselovvv.drinks.data.cocktails.cloud.CocktailsCloudMapper
import com.veselovvv.drinks.data.cocktails.cloud.CocktailsService
import com.veselovvv.drinks.domain.cocktails.BaseCocktailsDataToDomainMapper
import com.veselovvv.drinks.domain.cocktails.CocktailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    private companion object {
        const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"
    }

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).client(client).build()

    @Provides
    @Singleton
    fun provideCocktailsService(retrofit: Retrofit): CocktailsService =
        retrofit.create(CocktailsService::class.java)

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideCocktailsCloudDataSource(service: CocktailsService, gson: Gson): CocktailsCloudDataSource =
        CocktailsCloudDataSource.Base(service, gson)

    @Provides
    @Singleton
    fun provideToCocktailMapper(): ToCocktailMapper = ToCocktailMapper.Base()

    @Provides
    @Singleton
    fun provideCocktailsCloudMapper(toCocktailMapper: ToCocktailMapper): CocktailsCloudMapper =
        CocktailsCloudMapper.Base(toCocktailMapper)

    @Provides
    @Singleton
    fun provideCocktailsRepository(
        cloudDataSource: CocktailsCloudDataSource, cocktailsCloudMapper: CocktailsCloudMapper
    ): CocktailsRepository = BaseCocktailsRepository(cloudDataSource, cocktailsCloudMapper)
}