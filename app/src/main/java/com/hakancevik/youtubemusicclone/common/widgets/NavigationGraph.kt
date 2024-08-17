package com.hakancevik.youtubemusicclone.common.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hakancevik.youtubemusicclone.ui.explore.ExploreScreen
import com.hakancevik.youtubemusicclone.ui.home.HomeScreen
import com.hakancevik.youtubemusicclone.ui.library.LibraryScreen
import com.hakancevik.youtubemusicclone.ui.samples.SamplesScreen


fun NavBackStackEntry.getArgument(key: String): String {
    return arguments?.getString(key) ?: throw IllegalArgumentException("Argument $key is missing")
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    bottomSheetWidgetBounds: MutableState<Float?>,
    modifier: Modifier = Modifier // Modifier parametresi eklendi
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier // Modifier burada uygulanıyor
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Samples.route) { SamplesScreen(navController) }
        composable(Screen.Explore.route) { ExploreScreen(navController) }
        composable(Screen.Library.route) { LibraryScreen(navController) }
    }
}