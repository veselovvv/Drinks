package com.veselovvv.drinks.di

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
    fun provideCocktailDataToDbMapper(): CocktailDataToDbMapper = CocktailDataToDbMapper.Base()

    @Provides
    @Singleton
    fun provideCocktailsCacheDataSource(
        @ApplicationContext context: Context, mapper: CocktailDataToDbMapper
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
        cloudDataSource, cacheDataSource, cocktailsCloudMapper, cocktailsCacheMapper)

    @Provides
    @Singleton
    fun provideCocktailDetailsService(retrofit: Retrofit) : CocktailDetailsService =
        retrofit.create(CocktailDetailsService::class.java)

    @Provides
    @Singleton
    fun provideCocktailDetailsCloudDataSource(
        service: CocktailDetailsService, gson: Gson
    ): CocktailDetailsCloudDataSource = CocktailDetailsCloudDataSource.Base(service, gson)

    @Provides
    @Singleton
    fun provideCocktailDetailsDataToDbMapper(): CocktailDetailsDataToDbMapper =
        CocktailDetailsDataToDbMapper.Base()

    @Provides
    @Singleton
    fun provideCocktailDetailsCacheDataSource(
        @ApplicationContext context: Context, mapper: CocktailDetailsDataToDbMapper
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