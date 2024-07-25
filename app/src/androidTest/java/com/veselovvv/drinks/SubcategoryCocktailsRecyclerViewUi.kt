package com.veselovvv.drinks

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed

class SubcategoryCocktailsRecyclerViewUi : RecyclerViewUi(
    R.id.subcategory_cocktails_swipe_to_refresh,
    R.id.subcategory_cocktails_recycler_view
) {
    fun checkSubcategoryCocktailsListState(subcategoryCocktails: List<String>) {
        subcategoryCocktails.forEachIndexed { index, cocktail ->
            interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(index))
                .check(matches(withItemViewType(BASE_VIEW_TYPE)))
                .check(matches(isDisplayed()))
                .check(matches(
                    withRecyclerViewItemText(R.id.subcategory_cocktail_name_text_view, cocktail)
                ))
        }
    }

    /**
     * Described in getItemViewType() in SubcategoryCocktailsAdapter
     */
    companion object {
        private const val BASE_VIEW_TYPE = 0
    }
}
