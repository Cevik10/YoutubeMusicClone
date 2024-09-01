package com.hakancevik.youtubemusicclone.common.widgets

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hakancevik.youtubemusicclone.ui.explore.ExploreScreen
import com.hakancevik.youtubemusicclone.ui.home.HomeScreen
import com.hakancevik.youtubemusicclone.ui.home.trackdetail.TrackDetailScreen
import com.hakancevik.youtubemusicclone.ui.library.LibraryScreen
import com.hakancevik.youtubemusicclone.ui.musicplayer.service.MusicPlayerService
import com.hakancevik.youtubemusicclone.ui.samples.SamplesScreen


fun NavBackStackEntry.getArgument(key: String): String {
    return arguments?.getString(key) ?: throw IllegalArgumentException("Argument $key is missing")
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    serviceBinder: MusicPlayerService.MusicServiceBinder?,
    bottomSheetWidgetBounds: MutableState<Float?>,
    modifier: Modifier = Modifier // Modifier parametresi eklendi
) {

    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier // Modifier burada uygulanıyor
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                serviceBinder = serviceBinder,
                bottomSheetWidgetBounds = bottomSheetWidgetBounds
            )
        }
        composable(Screen.Samples.route) { SamplesScreen(navController) }
        composable(Screen.Explore.route) { ExploreScreen(navController) }
        composable(Screen.Library.route) { LibraryScreen(navController) }

        composable(
            route = "${Screen.TrackDetail.route}/{trackId}",
            arguments = listOf(navArgument("trackId") { type = NavType.StringType })
        ) { backStackEntry ->
            val trackId = backStackEntry.getArgument("trackId")
            TrackDetailScreen(
                trackId = trackId,
                serviceBinder = serviceBinder,
                context = LocalContext.current,
                sheetState = sheetState,
                coroutineScope = coroutineScope,
                bottomSheetWidgetBounds = bottomSheetWidgetBounds
            )
        }

    }
}