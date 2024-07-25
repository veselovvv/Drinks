package com.veselovvv.drinks

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed

class CocktailsRecyclerViewUi : RecyclerViewUi(
    R.id.cocktails_swipe_to_refresh,
    R.id.cocktails_recycler_view
) {
    fun checkCocktailsListState(cocktails: List<Pair<String, String>>) {
        cocktails.forEachIndexed { index, (name, category) ->
            interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(index))
                .check(matches(withItemViewType(BASE_VIEW_TYPE)))
                .check(matches(isDisplayed()))
                .check(matches(withRecyclerViewItemText(R.id.cocktail_name_text_view, name)))
                .check(matches(withRecyclerViewItemText(R.id.cocktail_category_text_view, category)))
        }
    }

    fun checkNoResultsState(text: String) {
        interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(withItemViewType(NO_RESULTS_VIEW_TYPE)))
            .check(matches(isDisplayed()))
            .check(matches(withRecyclerViewItemText(R.id.no_results_text_view, text)))
    }

    /**
     * Described in getItemViewType() in CocktailsAdapter
     */
    companion object {
        private const val NO_RESULTS_VIEW_TYPE = -1
        private const val BASE_VIEW_TYPE = 0
    }
}
