package com.veselovvv.drinks.core

import android.widget.LinearLayout
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.textview.MaterialTextView
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers

abstract class AbstractPage(protected val rootLayout: Int) {
    protected open val interaction: ViewInteraction = onView(
        allOf(
            isAssignableFrom(LinearLayout::class.java),
            withId(rootLayout)
        )
    )

    fun checkIsVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun checkIsNotVisible() {
        interaction.check(doesNotExist())
    }

    fun checkTextViewHasText(@IdRes textViewId: Int, text: String) {
        onView(
            Matchers.allOf(
                withId(textViewId),
                isAssignableFrom(MaterialTextView::class.java)
            )
        ).check(matches(withText(text)))
    }
}