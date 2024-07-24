package com.veselovvv.drinks

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matchers.allOf

class SubcategoriesRecyclerViewUi {
    private val interaction: ViewInteraction = onView(
        allOf(
            withParent(withId(R.id.subcategories_swipe_to_refresh)),
            withParent(isAssignableFrom(SwipeRefreshLayout::class.java)),
            withId(R.id.subcategories_recycler_view),
            isAssignableFrom(RecyclerView::class.java)
        )
    )

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
