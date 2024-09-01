package com.hakancevik.youtubemusicclone.common.widgets

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.FormatIndentDecrease
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.hakancevik.youtubemusicclone.R


sealed class Screen(val route: String, val icon: ImageVector?, @StringRes val resourceId: Int?) {
    data object Home : Screen("home", Icons.Filled.Home, R.string.title_home)
    data object Samples : Screen("samples", Icons.Filled.FormatIndentDecrease, R.string.title_samples)
    data object Explore : Screen("explore", Icons.Filled.Explore, R.string.title_explore)
    data object Library : Screen("library", Icons.Filled.LibraryBooks, R.string.title_library)
    data object TrackDetail : Screen("trackDetail", null, null) {
        fun createRoute(trackId: String) = "$route/$trackId"
    }
}

