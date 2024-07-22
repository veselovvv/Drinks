package com.veselovvv.drinks

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matchers.allOf

class RecyclerViewUi {
    private val interaction: ViewInteraction = onView(
        allOf(
            withParent(withId(R.id.cocktails_swipe_to_refresh)),
            withParent(isAssignableFrom(SwipeRefreshLayout::class.java)),
            withId(R.id.cocktails_recycler_view),
            isAssignableFrom(RecyclerView::class.java)
        )
    )

    fun checkCocktailsListState(cocktails: List<Pair<String, String>>) {
        cocktails.forEachIndexed { index, (name, category) ->
            interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(index))
                .check(matches(withItemViewType(BASE_VIEW_TYPE)))
                .check(matches(isDisplayed()))
                .check(matches(withRecyclerViewItemText(R.id.cocktail_name_text_view, name)))
                .check(matches(withRecyclerViewItemText(R.id.cocktail_category_text_view, category)))
        }
    }

    fun checkErrorState(message: String) {
        interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(withItemViewType(FAIL_VIEW_TYPE)))
            .check(matches(isDisplayed()))
            .check(matches(withRecyclerViewItemText(R.id.fail_message_text_view, message)))
    }

    fun clickTryAgainButton() {
        interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(withItemViewType(FAIL_VIEW_TYPE)))
            .perform(clickOnViewChild(R.id.fail_try_again_button))
    }

    fun checkNoResultsState(text: String) {
        interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(withItemViewType(NO_RESULTS_VIEW_TYPE)))
            .check(matches(isDisplayed()))
            .check(matches(withRecyclerViewItemText(R.id.no_results_text_view, text)))
    }

    fun clickOnItemInList(index: Int) {
        interaction.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(index, click()))
    }

    /**
     * Described in getItemViewType() in CocktailsAdapter
     */
    companion object {
        private const val NO_RESULTS_VIEW_TYPE = -1
        private const val BASE_VIEW_TYPE = 0
        private const val FAIL_VIEW_TYPE = 1
    }
}
