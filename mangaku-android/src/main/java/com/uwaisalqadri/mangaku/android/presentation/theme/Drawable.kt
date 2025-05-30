package com.uwaisalqadri.mangaku.android.presentation.theme

import androidx.annotation.DrawableRes
import com.uwaisalqadri.mangaku.android.R

/**
 * Holds references to drawable resource IDs used across the application.
 */
object R {
    /**
     * Drawable resource identifiers.
     */
    object Drawable {
        @DrawableRes
        val SearchIcon: Int = R.drawable.ic_search
        @DrawableRes
        val SlideIcon: Int = R.drawable.ic_slide
        @DrawableRes
        val StackIcon: Int = R.drawable.ic_stack
        @DrawableRes
        val CancelIcon: Int = R.drawable.ic_cancel
        @DrawableRes
        val BrowseActiveIcon: Int = R.drawable.ic_browse_active
        @DrawableRes
        val MangaActiveIcon: Int = R.drawable.ic_mymanga_active
    }
}