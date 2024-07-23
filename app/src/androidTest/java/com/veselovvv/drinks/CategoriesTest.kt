package com.veselovvv.drinks

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class CategoriesTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun init() {
        hiltRule.inject()
    }

    /**
     * Check Cocktails Page is visible
     * 1. Click on "Categories" tab in BottomNavigation
     * Check Categories Page is visible
     * Check categories state
     * 2. Recreate activity
     * Check Categories Page is visible
     * Check categories state
     */
    @Test
    fun loadCategories() {
        with(CocktailsPage()) {
            checkIsVisible()
            clickOnCategoriesTab()
        }

        with(CategoriesPage()) {
            checkIsVisible()
            checkCategoriesState(
                category1 = "Categories",
                category2 = "Glass",
                category3 = "Ingredients",
                category4 = "Alcohol"
            )
            activityScenarioRule.scenario.recreate()
            checkIsVisible()
            checkCategoriesState(
                category1 = "Categories",
                category2 = "Glass",
                category3 = "Ingredients",
                category4 = "Alcohol"
            )
        }
    }
}