package com.veselovvv.drinks

import androidx.test.espresso.Espresso.pressBack
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

    /**
     * Check Cocktails Page is visible
     * 1. Click on "Categories" tab in BottomNavigation
     * Check Categories Page is visible
     * Check categories state
     * 2. Click on "Categories" CardView
     * Check Subcategories Page is visible
     * Check subcategories list state
     * 3. Swipe to refresh
     * Check Subcategories Page is visible
     * Check subcategories list state
     * 4. Press back button
     * Check Subcategories Page is not visible
     * Check Categories Page is visible
     * Check categories state
     */
    @Test
    fun loadCategoriesSubcategoriesAndGoBack() {
        with(CocktailsPage()) {
            checkIsVisible()
            clickOnCategoriesTab()
        }

        val categoriesPage = CategoriesPage()

        with(categoriesPage) {
            checkIsVisible()
            checkCategoriesState(
                category1 = "Categories",
                category2 = "Glass",
                category3 = "Ingredients",
                category4 = "Alcohol"
            )
            clickOnCategoriesCardView()
        }

        val subcategoriesPage = SubcategoriesPage()

        with(subcategoriesPage) {
            checkIsVisible()
            checkSubcategoriesListState(
                subcategories = listOf("Ordinary Drink", "Cocktail", "Shake", "Coffee / Tea")
            )
            swipeToRefresh()
            checkIsVisible()
            checkSubcategoriesListState(
                subcategories = listOf("Ordinary Drink", "Cocktail", "Shake", "Coffee / Tea")
            )
        }

        pressBack()

        subcategoriesPage.checkIsNotVisible()
        with(categoriesPage) {
            checkIsVisible()
            checkCategoriesState(
                category1 = "Categories",
                category2 = "Glass",
                category3 = "Ingredients",
                category4 = "Alcohol"
            )
        }
    }

    /**
     * Check Cocktails Page is visible
     * 1. Click on "Categories" tab in BottomNavigation
     * Check Categories Page is visible
     * Check categories state
     * 2. Click on "Glass" CardView
     * Check Subcategories Page is visible
     * Check subcategories list state
     * 3. Swipe to refresh
     * Check Subcategories Page is visible
     * Check subcategories list state
     * 4. Press back button
     * Check Subcategories Page is not visible
     * Check Categories Page is visible
     * Check categories state
     */
    @Test
    fun loadGlassSubcategoriesAndGoBack() {
        with(CocktailsPage()) {
            checkIsVisible()
            clickOnCategoriesTab()
        }

        val categoriesPage = CategoriesPage()

        with(categoriesPage) {
            checkIsVisible()
            checkCategoriesState(
                category1 = "Categories",
                category2 = "Glass",
                category3 = "Ingredients",
                category4 = "Alcohol"
            )
            clickOnGlassCardView()
        }

        val subcategoriesPage = SubcategoriesPage()

        with(subcategoriesPage) {
            checkIsVisible()
            checkSubcategoriesListState(
                subcategories = listOf("Cocktail glass", "Whiskey glass", "Wine glass")
            )
            swipeToRefresh()
            checkIsVisible()
            checkSubcategoriesListState(
                subcategories = listOf("Cocktail glass", "Whiskey glass", "Wine glass")
            )
        }

        pressBack()

        subcategoriesPage.checkIsNotVisible()
        with(categoriesPage) {
            checkIsVisible()
            checkCategoriesState(
                category1 = "Categories",
                category2 = "Glass",
                category3 = "Ingredients",
                category4 = "Alcohol"
            )
        }
    }

    /**
     * Check Cocktails Page is visible
     * 1. Click on "Categories" tab in BottomNavigation
     * Check Categories Page is visible
     * Check categories state
     * 2. Click on "Ingredients" CardView
     * Check Subcategories Page is visible
     * Check subcategories list state
     * 3. Swipe to refresh
     * Check Subcategories Page is visible
     * Check subcategories list state
     * 4. Press back button
     * Check Subcategories Page is not visible
     * Check Categories Page is visible
     * Check categories state
     */
    @Test
    fun loadIngredientsSubcategoriesAndGoBack() {
        with(CocktailsPage()) {
            checkIsVisible()
            clickOnCategoriesTab()
        }

        val categoriesPage = CategoriesPage()

        with(categoriesPage) {
            checkIsVisible()
            checkCategoriesState(
                category1 = "Categories",
                category2 = "Glass",
                category3 = "Ingredients",
                category4 = "Alcohol"
            )
            clickOnIngredientsCardView()
        }

        val subcategoriesPage = SubcategoriesPage()

        with(subcategoriesPage) {
            checkIsVisible()
            checkSubcategoriesListState(
                subcategories = listOf("Gin", "Dark rum", "Sugar", "Milk")
            )
            swipeToRefresh()
            checkIsVisible()
            checkSubcategoriesListState(
                subcategories = listOf("Gin", "Dark rum", "Sugar", "Milk")
            )
        }

        pressBack()

        subcategoriesPage.checkIsNotVisible()
        with(categoriesPage) {
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