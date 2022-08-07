package com.veselovvv.drinks.presentation.subcategories

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.veselovvv.drinks.core.Mapper

interface SubcategoriesCommunication : Mapper {
    fun map(subcategories: List<SubcategoryUi>)
    fun observe(owner: LifecycleOwner, observer: Observer<List<SubcategoryUi>>)

    class Base : SubcategoriesCommunication {
        private val subcategoriesLiveData = MutableLiveData<List<SubcategoryUi>>()

        override fun map(subcategories: List<SubcategoryUi>) {
            subcategoriesLiveData.value = subcategories
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<SubcategoryUi>>) =
            subcategoriesLiveData.observe(owner, observer)
    }
}