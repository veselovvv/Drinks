package com.veselovvv.drinks

import android.widget.LinearLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matchers.allOf

class SwipeToRefreshUi {
    private val interaction: ViewInteraction = onView(
        allOf(
            withParent(withId(R.id.cocktails_root_layout)),
            withParent(isAssignableFrom(LinearLayout::class.java)),
            withId(R.id.cocktails_swipe_to_refresh),
            isAssignableFrom(SwipeRefreshLayout::class.java)
        )
    )

    fun swipeToRefresh() {
        interaction.perform(swipeDown())
    }
}
