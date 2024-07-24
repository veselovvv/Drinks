package com.veselovvv.drinks

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed

class SubcategoriesRecyclerViewUi : RecyclerViewUi(
    R.id.subcategories_swipe_to_refresh,
    R.id.subcategories_recycler_view
) {
    fun checkSubcategoriesListState(subcategories: List<String>) {
        subcategories.forEachIndexed { index, subcategory ->
            interaction.perform(scrollToPosition<RecyclerView.ViewHolder>(index))
                .check(matches(withItemViewType(BASE_VIEW_TYPE)))
                .check(matches(isDisplayed()))
                .check(matches(withRecyclerViewItemText(R.id.subcategory_text_view, subcategory)))
        }
    }

    /**
     * Described in getItemViewType() in SubcategoriesAdapter
     */
    companion object {
        private const val BASE_VIEW_TYPE = 0
    }
}
