package com.hakancevik.youtubemusicclone.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hakancevik.data.model.track.Track
import com.hakancevik.domain.entity.ResultData
import com.hakancevik.youtubemusicclone.common.theme.AppTheme
import com.hakancevik.youtubemusicclone.common.widgets.ErrorScreen
import com.hakancevik.youtubemusicclone.common.widgets.LoadingScreen
import com.hakancevik.youtubemusicclone.common.widgets.Screen
import com.hakancevik.youtubemusicclone.ui.home.components.CategoriesWidget
import com.hakancevik.youtubemusicclone.ui.home.components.HomeAppBar
import com.hakancevik.youtubemusicclone.ui.home.trackdetail.MusicPlayerDetailBottomSheet
import com.hakancevik.youtubemusicclone.ui.musicplayer.service.MusicPlayerService

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    serviceBinder: MusicPlayerService.MusicServiceBinder?,
    bottomSheetWidgetBounds: MutableState<Float?>,
) {
    AppTheme {
        HomeContent(navController = navController, viewModel = viewModel,serviceBinder = serviceBinder, bottomSheetWidgetBounds = bottomSheetWidgetBounds)
    }
}

@Composable

fun HomeContent(
    navController: NavController,
    viewModel: HomeViewModel,
    serviceBinder: MusicPlayerService.MusicServiceBinder?,
    bottomSheetWidgetBounds: MutableState<Float?>,
) {

    val playlistState by viewModel.playlist.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        HomeAppBar(
            onPlayClick = { },
            onNotificationsClick = { },
            onSearchClick = { },
            onAccountClick = { }
        )


        when (playlistState) {
            is ResultData.Loading -> {
                LoadingScreen()
            }

            is ResultData.Success -> {
                val playlist = (playlistState as ResultData.Success).data

                CategoriesWidget(tracks = playlist.tracks.data, playlistTitle = playlist.title, onTrackClick = { trackData ->
                    navController.navigate(Screen.TrackDetail.createRoute(trackData.id))
                })

            }

            is ResultData.Error -> {
                ErrorScreen(message = "Error: ${(playlistState as ResultData.Error).exception.message}")
            }
        }


        MusicPlayerDetailBottomSheet(
            serviceBinder = serviceBinder,
            bottomSheetWidgetBounds = bottomSheetWidgetBounds,
            modifier = Modifier.align(Alignment.BottomCenter),
            track = Track(Albu)
        )


    }
}



