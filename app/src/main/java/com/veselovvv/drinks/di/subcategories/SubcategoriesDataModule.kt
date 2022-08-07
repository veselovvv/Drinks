package com.veselovvv.drinks.di.subcategories

import com.google.gson.Gson
import com.veselovvv.drinks.data.subcategories.SubcategoriesRepository
import com.veselovvv.drinks.data.subcategories.ToSubcategoryMapper
import com.veselovvv.drinks.data.subcategories.cloud.SubcategoriesCloudDataSource
import com.veselovvv.drinks.data.subcategories.cloud.SubcategoriesCloudMapper
import com.veselovvv.drinks.data.subcategories.cloud.SubcategoriesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SubcategoriesDataModule {
    @Provides
    @Singleton
    fun provideSubcategoriesService(retrofit: Retrofit): SubcategoriesService =
        retrofit.create(SubcategoriesService::class.java)

    @Provides
    @Singleton
    fun provideSubcategoriesCloudDataSource(
        service: SubcategoriesService, gson: Gson
    ): SubcategoriesCloudDataSource = SubcategoriesCloudDataSource.Base(service, gson)

    @Provides
    @Singleton
    fun provideToSubcategoryMapper(): ToSubcategoryMapper = ToSubcategoryMapper.Base()

    @Provides
    @Singleton
    fun provideSubcategoriesCloudMapper(
        toSubcategoryMapper: ToSubcategoryMapper
    ): SubcategoriesCloudMapper = SubcategoriesCloudMapper.Base(toSubcategoryMapper)

    @Provides
    @Singleton
    fun provideSubcategoriesRepository(
        cloudDataSource: SubcategoriesCloudDataSource,
        subcategoriesCloudMapper: SubcategoriesCloudMapper
    ): SubcategoriesRepository = SubcategoriesRepository.Base(cloudDataSource, subcategoriesCloudMapper)
}