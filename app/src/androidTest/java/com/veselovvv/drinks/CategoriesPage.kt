package com.veselovvv.drinks

import android.widget.GridLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matchers.allOf

class CategoriesPage : AbstractPage(R.id.categories_root_layout) {
    override val interaction: ViewInteraction = onView(
        allOf(
            isAssignableFrom(GridLayout::class.java),
            withId(rootLayout)
        )
    )

    fun checkCategoriesState(
        category1: String, category2: String, category3: String, category4: String
    ) {
        checkTextViewHasText(R.id.categories_text_view, category1)
        checkTextViewHasText(R.id.glass_text_view, category2)
        checkTextViewHasText(R.id.ingredients_text_view, category3)
        checkTextViewHasText(R.id.alcohol_text_view, category4)
    }
}
