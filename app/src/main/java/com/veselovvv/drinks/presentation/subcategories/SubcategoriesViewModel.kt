package com.veselovvv.drinks.presentation.subcategories

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.drinks.domain.subcategories.FetchSubcategoriesUseCase
import com.veselovvv.drinks.domain.subcategories.SubcategoriesDomainToUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SubcategoriesViewModel @Inject constructor(
    private val fetchSubcategoriesUseCase: FetchSubcategoriesUseCase,
    private val mapper: SubcategoriesDomainToUiMapper,
    private val communication: SubcategoriesCommunication
) : ViewModel() {
    fun fetchSubcategories(categoryKey: String) {
        communication.map(listOf(SubcategoryUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = fetchSubcategoriesUseCase.execute(categoryKey)
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<SubcategoryUi>>) =
        communication.observe(owner, observer)
}