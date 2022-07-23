package com.veselovvv.drinks.di.searchingredient

import com.veselovvv.drinks.core.ResourceProvider
import com.veselovvv.drinks.data.searchingredient.IngredientDataToDomainMapper
import com.veselovvv.drinks.data.searchingredient.IngredientRepository
import com.veselovvv.drinks.data.searchingredient.IngredientsDataToDomainMapper
import com.veselovvv.drinks.domain.searchingredient.*
import com.veselovvv.drinks.presentation.searchingredient.BaseIngredientDomainToUiMapper
import com.veselovvv.drinks.presentation.searchingredient.BaseIngredientsDomainToUiMapper
import com.veselovvv.drinks.presentation.searchingredient.IngredientCommunication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class SearchIngredientDomainModule {
    @Provides
    fun provideIngredientDataToDomainMapper(): IngredientDataToDomainMapper =
        BaseIngredientDataToDomainMapper()

    @Provides
    fun provideIngredientsDataToDomainMapper(
        ingredientMapper: IngredientDataToDomainMapper
    ): IngredientsDataToDomainMapper = BaseIngredientsDataToDomainMapper(ingredientMapper)

    @Provides
    fun provideFetchIngredientUseCase(
        ingredientRepository: IngredientRepository,
        ingredientsDataToDomainMapper: IngredientsDataToDomainMapper
    ): FetchIngredientUseCase = FetchIngredientUseCase(
        ingredientRepository, ingredientsDataToDomainMapper
    )

    @Provides
    fun provideIngredientDomainToUiMapper(): IngredientDomainToUiMapper =
        BaseIngredientDomainToUiMapper()

    @Provides
    fun provideIngredientsDomainToUiMapper(
        resourceProvider: ResourceProvider,
        ingredientMapper: IngredientDomainToUiMapper
    ): IngredientsDomainToUiMapper = BaseIngredientsDomainToUiMapper(
        resourceProvider, ingredientMapper
    )

    @Provides
    fun provideIngredientCommunication(): IngredientCommunication = IngredientCommunication.Base()
}