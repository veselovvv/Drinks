package com.veselovvv.drinks.cocktails

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.veselovvv.drinks.R

class BottomNavigationUi {
    fun clickOnTab(@IdRes tabId: Int) {
        onView(withId(tabId)).perform(click())
    }

    fun clickOnCategoriesTab() = clickOnTab(R.id.categoriesFragment)
    fun clickOnRandomTab() = clickOnTab(R.id.randomCocktailFragment)
    fun clickOnIngredientsTab() = clickOnTab(R.id.searchIngredientFragment)
}
