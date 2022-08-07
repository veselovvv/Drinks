package com.veselovvv.drinks.di.subcategories

import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.data.subcategories.SubcategoriesDataToDomainMapper
import com.veselovvv.drinks.data.subcategories.SubcategoriesRepository
import com.veselovvv.drinks.data.subcategories.SubcategoryDataToDomainMapper
import com.veselovvv.drinks.domain.subcategories.*
import com.veselovvv.drinks.presentation.subcategories.BaseSubcategoriesDomainToUiMapper
import com.veselovvv.drinks.presentation.subcategories.BaseSubcategoryDomainToUiMapper
import com.veselovvv.drinks.presentation.subcategories.SubcategoriesCommunication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class SubcategoriesDomainModule {
    @Provides
    fun provideSubcategoryDataToDomainMapper(): SubcategoryDataToDomainMapper =
        BaseSubcategoryDataToDomainMapper()

    @Provides
    fun provideSubcategoriesDataToDomainMapper(
        subcategoriesMapper: SubcategoryDataToDomainMapper
    ): SubcategoriesDataToDomainMapper = BaseSubcategoriesDataToDomainMapper(subcategoriesMapper)

    @Provides
    fun provideFetchSubcategoriesUseCase(
        subcategoriesRepository: SubcategoriesRepository,
        mapper: SubcategoriesDataToDomainMapper
    ): FetchSubcategoriesUseCase = FetchSubcategoriesUseCase(subcategoriesRepository, mapper)

    @Provides
    fun provideSubcategoryDomainToUiMapper(): SubcategoryDomainToUiMapper = BaseSubcategoryDomainToUiMapper()

    @Provides
    fun provideSubcategoriesDomainToUiMapper(
        resourceProvider: ResourceProvider,
        subcategoryMapper: SubcategoryDomainToUiMapper
    ): SubcategoriesDomainToUiMapper = BaseSubcategoriesDomainToUiMapper(resourceProvider, subcategoryMapper)

    @Provides
    fun provideSubcategoriesCommunication(): SubcategoriesCommunication = SubcategoriesCommunication.Base()
}