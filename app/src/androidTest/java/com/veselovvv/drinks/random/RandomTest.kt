package com.veselovvv.drinks.random

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.veselovvv.drinks.MainActivity
import com.veselovvv.drinks.cocktails.CocktailDetailsPage
import com.veselovvv.drinks.cocktails.CocktailsPage
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class RandomTest {
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
     * 1. Click on "Random" tab in BottomNavigation
     * Check Cocktail Details Page is visible
     * Check cocktail details state
     * 2. Swipe to refresh
     * Check error state with text "No connection. Please try again!"
     * 3. Click "Try again" button
     * Check Cocktail Details Page is visible
     * Check cocktail details state
     */
    @Test
    fun loadRandomCocktail() {
        with(CocktailsPage()) {
            checkIsVisible()
            clickOnRandomTab()
        }

        with(CocktailDetailsPage()) {
            checkIsVisible()
            checkCocktailDetailsState(
                name = "Margarita",
                category = "Cocktail",
                alcoholic = "Alcoholic",
                glass = "Cocktail glass",
                instructions = "Rub the rim of the glass with the lime slice to make the salt stick to it.",
                ingredient1 = "Tequila",
                ingredient2 = "Triple sec",
                ingredient3 = "Lime juice",
                ingredient4 = "Salt"
            )
            swipeToRefresh()
            checkErrorState(message = "No connection. Please try again!")
            clickTryAgainButton()
            checkIsVisible()
            checkCocktailDetailsState(
                name = "Margarita",
                category = "Cocktail",
                alcoholic = "Alcoholic",
                glass = "Cocktail glass",
                instructions = "Rub the rim of the glass with the lime slice to make the salt stick to it.",
                ingredient1 = "Tequila",
                ingredient2 = "Triple sec",
                ingredient3 = "Lime juice",
                ingredient4 = "Salt"
            )
        }
    }
}