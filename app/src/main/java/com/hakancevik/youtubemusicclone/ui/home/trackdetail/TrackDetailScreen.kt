package com.hakancevik.youtubemusicclone.ui.home.trackdetail

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hakancevik.domain.entity.MediaPlayerState
import com.hakancevik.domain.entity.ResultData
import com.hakancevik.youtubemusicclone.R
import com.hakancevik.youtubemusicclone.common.Constants
import com.hakancevik.youtubemusicclone.common.dpToPx
import com.hakancevik.youtubemusicclone.common.pxToDp
import com.hakancevik.youtubemusicclone.common.theme.AppTheme
import com.hakancevik.youtubemusicclone.common.widgets.DynamicAsyncImage
import com.hakancevik.youtubemusicclone.common.widgets.LoadingScreen
import com.hakancevik.youtubemusicclone.ui.home.createDummyTrack
import com.hakancevik.youtubemusicclone.ui.home.trackdetail.components.AnimatedPlayPauseIcon
import com.hakancevik.youtubemusicclone.ui.home.trackdetail.components.SliderSeekBar
import com.hakancevik.youtubemusicclone.ui.home.trackdetail.components.SongInformationWidget
import com.hakancevik.youtubemusicclone.ui.musicplayer.service.MusicPlayerService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TrackDetailScreen(
    trackId: String,
    serviceBinder: MusicPlayerService.MusicServiceBinder?,
    context: Context = LocalContext.current,
    sheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
    bottomSheetWidgetBounds: MutableState<Float?>,
    viewModel: TrackDetailViewModel = hiltViewModel(),

    ) {

    LaunchedEffect(trackId) {
        viewModel.getTrack(trackId)
    }

    val trackState by viewModel.trackState.collectAsState()

    when (val result = trackState) {
        is ResultData.Loading -> {
            // Display a loading indicator
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LoadingScreen()
            }
        }

        is ResultData.Success -> {
            val track = result.data
            Log.d(
                "TrackDetailScreen",
                "Displaying details for trackId: $trackId with title: ${track.title}"
            )


            // START TRACK

            // Display track details
            Box(modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned { coordinates ->
                    val toDp = pxToDp(context = context, coordinates.boundsInRoot().top)

                    if (toDp != 0f) {
                        bottomSheetWidgetBounds.value = toDp
                    }
                }
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.onTertiary,
                            MaterialTheme.colorScheme.onSurface,
                            MaterialTheme.colorScheme.background
                        ), startY = Constants.DEFAULT_VALUE, endY = dpToPx(context, 1200f).toFloat()
                    )
                )) {
                Column {
                    PlayerDetailTopToolbar(sheetState, coroutineScope)
                    Spacer(modifier = Modifier.height(12.dp))
                    DynamicAsyncImage(
                        imageUrl = track.album?.coverMediumUrl
                            ?: "https://picsum.photos/200/300", // Use the track's image
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    SongInformationWidget(track) // Pass track data to display
                    SliderSeekBar(serviceBinder)
                    ActionButtons(serviceBinder, track.previewUrl ?: "https://docs.google.com/uc?export=open&id=1IBdOyBPy9BO2TFcDTi1wCBk9EcacbKv0")
                }
            }
        }

        is ResultData.Error -> {
            // Handle error state
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error loading track: ${result.exception.message}")
            }
        }
    }


}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlayerDetailTopToolbar(sheetState: ModalBottomSheetState, coroutineScope: CoroutineScope) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {
        Icon(imageVector = Icons.Filled.KeyboardArrowDown,
            contentDescription = stringResource(id = R.string.cd_down_arrow),
            tint = Color.White,
            modifier = Modifier
                .size(60.dp)
                .padding(12.dp)
                .align(Alignment.CenterVertically)
                .testTag("downArrowIcon")
                .clickable {
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                })
        Text(
            text = "Fats Domino Radio",
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterVertically)
                .weight(1f)
                .padding(end = 12.dp),
            color = Color.White
        )
        Icon(
            imageVector = Icons.Filled.MoreHoriz,
            contentDescription = stringResource(id = R.string.content_description),
            tint = Color.White,
            modifier = Modifier
                .size(50.dp)
                .padding(12.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun ActionButtons(serviceBinder: MusicPlayerService.MusicServiceBinder?, trackUrl: String) {
    val playerState = remember { mutableStateOf(MediaPlayerState.STOPPED) }
    val context = LocalContext.current

    LaunchedEffect(serviceBinder) {
        serviceBinder?.service?.mediaPlayerManager?.playerState?.collect { state ->
            playerState.value = state
        }
    }

    Row {
        Icon(
            imageVector = Icons.Filled.Shuffle,
            contentDescription = stringResource(id = R.string.content_description),
            tint = Color.White,
            modifier = Modifier
                .size(55.dp)
                .padding(12.dp)
                .align(Alignment.CenterVertically)
                .weight(0.7f)
        )
        Icon(
            imageVector = Icons.Filled.SkipPrevious,
            contentDescription = stringResource(id = R.string.content_description),
            tint = Color.White,
            modifier = Modifier
                .size(70.dp)
                .padding(12.dp)
                .align(Alignment.CenterVertically)
                .weight(0.7f)
        )
        AnimatedPlayPauseIcon(playerState, onPlayPauseClicked = { action ->
            val intent = Intent(context, MusicPlayerService::class.java).apply {
                this.action = action
                putExtra("TRACK_URL", trackUrl)
            }
            context.startService(intent)
        })
        Icon(
            imageVector = Icons.Filled.SkipNext,
            contentDescription = stringResource(id = R.string.content_description),
            tint = Color.White,
            modifier = Modifier
                .size(70.dp)
                .padding(12.dp)
                .align(Alignment.CenterVertically)
                .weight(0.7f)
        )
        Icon(
            imageVector = Icons.Filled.Replay,
            contentDescription = stringResource(id = R.string.content_description),
            tint = Color.White,
            modifier = Modifier
                .size(55.dp)
                .padding(12.dp)
                .align(Alignment.CenterVertically)
                .weight(0.7f)
        )
    }
}

@Preview(device = "id:pixel_5")
@Composable
fun SongInformationWidgetPreview() {
    AppTheme {
        //SongInformationWidget()
    }
}

@Preview(device = "id:pixel_5")
@Composable
fun AnimatedPlayPauseIconPreview() {
    val context = LocalContext.current
    AppTheme {
        AnimatedPlayPauseIcon(playerState = remember { mutableStateOf(MediaPlayerState.STOPPED) },
            onPlayPauseClicked = { action ->
                val intent = Intent(context, MusicPlayerService::class.java).apply {
                    this.action = action
                }
                context.startService(intent)
            })
    }
}

@Preview(device = "id:pixel_5")
@Composable
fun SliderSeekBarPreview() {
    AppTheme {
        SliderSeekBar(null)
    }
}

@Preview(device = "id:pixel_5")
@Composable
fun ActionButtonsPreview() {
    AppTheme {
        ActionButtons(null, "https://docs.google.com/uc?export=open&id=1IBdOyBPy9BO2TFcDTi1wCBk9EcacbKv0")
    }
}


//@Composable
//fun TrackDetailScreen(
//    trackId: String,
//    viewModel: TrackDetailViewModel = hiltViewModel()
//) {
//    LaunchedEffect(trackId) {
//        viewModel.getTrack(trackId)
//    }
//
//    val trackState by viewModel.trackState.collectAsState()
//
//    when (trackState) {
//        is ResultData.Loading -> {
//            LoadingScreen()
//        }
//
//        is ResultData.Success -> {
//            val track = (trackState as ResultData.Success).data
//            // Display track details
//            println("Track Title: ${track.title}")
//        }
//
//        is ResultData.Error -> {
//            ErrorScreen(message = "Error: ${(trackState as ResultData.Error).exception.message}")
//        }
//    }
//}

