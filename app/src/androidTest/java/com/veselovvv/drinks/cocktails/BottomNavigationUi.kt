package com.veselovvv.drinks.cocktails

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.veselovvv.drinks.R

class BottomNavigationUi {
    fun clickOnCategoriesTab() {
        onView(withId(R.id.categoriesFragment)).perform(click())
    }
}
