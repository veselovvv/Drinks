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
}
