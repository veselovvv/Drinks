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
class CocktailsTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun init() {
        hiltRule.inject()
    }

    /**
     * Check cocktails list state
     * 1. Recreate activity
     * Check cocktails list state
     * 2. Swipe to refresh
     * Check error state with text "No connection. Please try again!"
     * 3. Click "Try again" button
     * Check cocktails list state
     * 4. Recreate activity
     * Check cocktails list state
     */
    @Test
    fun loadCocktails() = with(CocktailsPage()) {
        checkCocktailsListState(
            cocktails = listOf(
                Pair("Margarita", "Ordinal"),
                Pair("Martini", "Ordinal"),
                Pair("Clover Club", "Ordinary Drink")
            )
        )
        activityScenarioRule.scenario.recreate()
        checkCocktailsListState(
            cocktails = listOf(
                Pair("Margarita", "Ordinal"),
                Pair("Martini", "Ordinal"),
                Pair("Clover Club", "Ordinary Drink")
            )
        )
        swipeToRefresh()
        checkErrorState(message = "No connection. Please try again!")
        clickTryAgainButton()
        checkCocktailsListState(
            cocktails = listOf(
                Pair("Margarita", "Ordinal"),
                Pair("Martini", "Ordinal"),
                Pair("Clover Club", "Ordinary Drink")
            )
        )
        activityScenarioRule.scenario.recreate()
        checkCocktailsListState(
            cocktails = listOf(
                Pair("Margarita", "Ordinal"),
                Pair("Martini", "Ordinal"),
                Pair("Clover Club", "Ordinary Drink")
            )
        )
    }

    /**
     * Check cocktails list state
     * 1. Click search button
     * Check search view state
     * 2. CLick back search button
     * Check cocktails list state
     * 3. Click search button
     * Check search view state
     * 4. Type "Mart" in search view
     * Check cocktails list state with found cocktail
     * 5. CLick back search button
     * Check cocktails list state
     * 6. Click search button
     * Check search view state
     * 7. Type "Zor" in search view
     * Check no results state with text "No results to show"
     * 8. Type "Mart" in search view
     * Check cocktails list state with found cocktail
     * 9. CLick back search button
     * Check cocktails list state
     */
    @Test
    fun searchCocktails() = with(CocktailsPage()) {
        checkCocktailsListState(
            cocktails = listOf(
                Pair("Margarita", "Ordinal"),
                Pair("Martini", "Ordinal"),
                Pair("Clover Club", "Ordinary Drink")
            )
        )
        clickSearchButton()
        checkSearchViewState()
        clickBackSearchButton()
        checkCocktailsListState(
            cocktails = listOf(
                Pair("Margarita", "Ordinal"),
                Pair("Martini", "Ordinal"),
                Pair("Clover Club", "Ordinary Drink")
            )
        )
        clickSearchButton()
        checkSearchViewState()
        typeInSearchView(text = "Mart")
        checkCocktailsListState(
            cocktails = listOf(
                Pair("Martini", "Ordinal")
            )
        )
        clickBackSearchButton()
        checkCocktailsListState(
            cocktails = listOf(
                Pair("Margarita", "Ordinal"),
                Pair("Martini", "Ordinal"),
                Pair("Clover Club", "Ordinary Drink")
            )
        )
        clickSearchButton()
        checkSearchViewState()
        typeInSearchView(text = "Zor")
        checkNoResultsState(text = "No results to show")
        typeInSearchView(text = "Mart")
        checkCocktailsListState(
            cocktails = listOf(
                Pair("Martini", "Ordinal")
            )
        )
        clickBackSearchButton()
        checkCocktailsListState(
            cocktails = listOf(
                Pair("Margarita", "Ordinal"),
                Pair("Martini", "Ordinal"),
                Pair("Clover Club", "Ordinary Drink")
            )
        )
    }
}
