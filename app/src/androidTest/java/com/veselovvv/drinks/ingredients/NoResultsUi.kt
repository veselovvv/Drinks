package com.veselovvv.drinks.ingredients

import android.widget.LinearLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.textview.MaterialTextView
import com.veselovvv.drinks.R
import org.hamcrest.Matchers.allOf

class NoResultsUi {
    fun checkNoResultsState(text: String) {
        onView(
            allOf(
                withParent(withId(R.id.search_ingredient_no_results_layout)),
                withParent(isAssignableFrom(LinearLayout::class.java)),
                withId(R.id.no_results_text_view),
                isAssignableFrom(MaterialTextView::class.java)
            )
        ).check(matches(withText(text)))
    }
}
