package com.veselovvv.drinks.ingredients

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.veselovvv.drinks.MainActivity
import com.veselovvv.drinks.cocktails.CocktailsPage
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class IngredientsTest {
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
     * 1. Click on "Ingredients" tab in BottomNavigation
     * Check Search Ingredient Page is visible
     * Check initial state with text "Click on the search button to find an ingredient"
     * 2. Recreate activity
     * Check Search Ingredient Page is visible
     * Check initial state with text "Click on the search button to find an ingredient"
     * 3. Click search button
     * Check search view state
     * 4. CLick back search button
     * Check Search Ingredient Page is visible
     * Check initial state with text "Click on the search button to find an ingredient"
     * 5. Click search button
     * Check search view state
     * 6. Type "fghkhk" in search view
     * 7. Click search button on keyboard
     * Check error state with text "No connection. Please try again!"
     * 8. CLick back search button
     * 9. Click search button
     * Check search view state
     * 10. Type "fghkhk" in search view
     * 11. Click search button on keyboard
     * Check no results state with text "No results to show"
     * 12. Recreate activity
     * Check no results state with text "No results to show"
     * 13. Click search button
     * 14. Type "Fresh Mint" in search view
     * 15. Click search button on keyboard
     * Check Search Ingredient Page is visible
     * Check ingredient state
     * 16. Recreate activity
     * Check Search Ingredient Page is visible
     * Check ingredient state
     */
    @Test
    fun searchIngredient() {
        with(CocktailsPage()) {
            checkIsVisible()
            clickOnIngredientsTab()
        }

        with(SearchIngredientPage()) {
            checkIsVisible()
            checkInitialState(text = "Click on the search button to find an ingredient")

            activityScenarioRule.scenario.recreate()

            checkIsVisible()
            checkInitialState(text = "Click on the search button to find an ingredient")

            clickSearchButton()
            checkSearchViewState()
            clickBackSearchButton()
            checkIsVisible()
            checkInitialState(text = "Click on the search button to find an ingredient")

            clickSearchButton()
            checkSearchViewState()
            typeInSearchView(text = "fghkhk")
            clickSearchButtonOnKeyboard()
            checkErrorState(message = "No connection. Please try again!")

            clickBackSearchButton()
            clickSearchButton()
            checkSearchViewState()
            typeInSearchView(text = "fghkhk")
            clickSearchButtonOnKeyboard()

            checkNoResultsState(text = "No results to show")
            activityScenarioRule.scenario.recreate()
            checkNoResultsState(text = "No results to show")

            clickSearchButton()
            typeInSearchView(text = "Fresh Mint")
            clickSearchButtonOnKeyboard()
            checkIsVisible()
            checkIngredientState(
                name = "Fresh Mint",
                type = "Garnish",
                alcohol = "No",
                abv = "-",
                description = "Lamiaceae or Labiatae is a family of flowering plants commonly known as the mint or deadnettle family."
            )

            activityScenarioRule.scenario.recreate()

            checkIsVisible()
            checkIngredientState(
                name = "Fresh Mint",
                type = "Garnish",
                alcohol = "No",
                abv = "-",
                description = "Lamiaceae or Labiatae is a family of flowering plants commonly known as the mint or deadnettle family."
            )
        }
    }
}