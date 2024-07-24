package com.veselovvv.drinks

import android.widget.GridLayout
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.google.android.material.card.MaterialCardView
import org.hamcrest.Matchers.allOf

class CardViewUi {
    fun click(@IdRes cardViewId: Int) {
        onView(
            allOf(
                withParent(withId(R.id.categories_root_layout)),
                withParent(isAssignableFrom(GridLayout::class.java)),
                withId(cardViewId),
                isAssignableFrom(MaterialCardView::class.java)
            )
        ).perform(click())
    }
}
