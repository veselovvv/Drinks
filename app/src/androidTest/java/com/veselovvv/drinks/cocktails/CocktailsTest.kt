package com.veselovvv.drinks.cocktails

import androidx.test.espresso.Espresso.pressBack
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.veselovvv.drinks.MainActivity
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

    /**
     * Check Cocktails Page is visible
     * Check cocktails list state
     * 1. Click on first item in list (index = 0)
     * Check Cocktail Details Page is visible
     * Check cocktail details state
     * 2. Recreate activity
     * Check Cocktail Details Page is visible
     * Check cocktail details state
     * 3. Swipe to refresh
     * Check error state with text "No connection. Please try again!"
     * 4. Click "Try again" button
     * Check Cocktail Details Page is visible
     * Check cocktail details state
     * 5. Recreate activity
     * Check Cocktail Details Page is visible
     * Check cocktail details state
     * 6. Press back button
     * Check Cocktail Details Page is not visible
     * Check Cocktails Page is visible
     * Check cocktails list state
     * 7. Recreate activity
     * Check Cocktail Details Page is not visible
     * Check Cocktails Page is visible
     * Check cocktails list state
     */
    @Test
    fun loadCocktailDetailsAndGoBack() {
        val cocktailsPage = CocktailsPage()

        with(cocktailsPage) {
            checkIsVisible()
            checkCocktailsListState(
                cocktails = listOf(
                    Pair("Margarita", "Ordinal"),
                    Pair("Martini", "Ordinal"),
                    Pair("Clover Club", "Ordinary Drink")
                )
            )
            clickOnItemInList(index = 0)
        }

        val cocktailDetailsPage = CocktailDetailsPage()

        with(cocktailDetailsPage) {
            checkIsVisible()
            checkCocktailDetailsState(
                name = "Margarita",
                category = "Ordinal",
                alcoholic = "Alcoholic",
                glass = "Cocktail glass",
                instructions = "Rub the rim of the glass with the lime slice to make the salt stick to it.",
                ingredient1 = "Tequila",
                ingredient2 = "Triple sec",
                ingredient3 = "Lime juice",
                ingredient4 = "Salt"
            )
            activityScenarioRule.scenario.recreate()
            checkIsVisible()
            checkCocktailDetailsState(
                name = "Margarita",
                category = "Ordinal",
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
                category = "Ordinal",
                alcoholic = "Alcoholic",
                glass = "Cocktail glass",
                instructions = "Rub the rim of the glass with the lime slice to make the salt stick to it.",
                ingredient1 = "Tequila",
                ingredient2 = "Triple sec",
                ingredient3 = "Lime juice",
                ingredient4 = "Salt"
            )
            activityScenarioRule.scenario.recreate()
            checkIsVisible()
            checkCocktailDetailsState(
                name = "Margarita",
                category = "Ordinal",
                alcoholic = "Alcoholic",
                glass = "Cocktail glass",
                instructions = "Rub the rim of the glass with the lime slice to make the salt stick to it.",
                ingredient1 = "Tequila",
                ingredient2 = "Triple sec",
                ingredient3 = "Lime juice",
                ingredient4 = "Salt"
            )
        }

        pressBack()

        cocktailDetailsPage.checkIsNotVisible()
        with(cocktailsPage) {
            checkIsVisible()
            checkCocktailsListState(
                cocktails = listOf(
                    Pair("Margarita", "Ordinal"),
                    Pair("Martini", "Ordinal"),
                    Pair("Clover Club", "Ordinary Drink")
                )
            )
        }
        activityScenarioRule.scenario.recreate()
        cocktailDetailsPage.checkIsNotVisible()
        with(cocktailsPage) {
            checkIsVisible()
            checkCocktailsListState(
                cocktails = listOf(
                    Pair("Margarita", "Ordinal"),
                    Pair("Martini", "Ordinal"),
                    Pair("Clover Club", "Ordinary Drink")
                )
            )
        }
    }
}
