package com.hakancevik.youtubemusicclone.ui.home.trackdetail

import android.content.Context
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hakancevik.data.model.track.TrackDTO
import com.hakancevik.domain.entity.trackdata.TrackData
import com.hakancevik.youtubemusicclone.ui.musicplayer.service.MusicPlayerService
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MusicPlayerDetailBottomSheet(
    trackData: TrackData,
    serviceBinder: MusicPlayerService.MusicServiceBinder?,
    bottomSheetWidgetBounds: MutableState<Float?>,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()
    val context: Context = LocalContext.current

    ModalBottomSheetLayout(sheetState = sheetState, sheetContent = {
        TrackDetailScreen(
            trackId = "",
            serviceBinder = serviceBinder,
            sheetState = sheetState,
            coroutineScope = coroutineScope,
            bottomSheetWidgetBounds = bottomSheetWidgetBounds
        )
    }) {
        MusicPlayerBottomWidget(modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
            //.padding(bottom = 80.dp, start = 8.dp, end = 8.dp)
            .height(65.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.onTertiary)
            .clickable {
                coroutineScope.launch { sheetState.show() }
            },
            imageUrl = trackData.album?.coverMediumUrl ?: "https://fastly.picsum.photos/id/626/200/300.jpg?hmac=8P_lvCUkxcubJb1bckQk2YQymRoW6JdkOgtL4ThZMjw",
            title = trackData.title ?: "song title",
            singer = trackData.artist?.name ?: "singer name",
            serviceBinder = serviceBinder,
            onPlayPauseClicked = { action ->
                val intent = Intent(context, MusicPlayerService::class.java).apply {
                    this.action = action
                }
                context.startService(intent)
            })
    }
    BackHandler(enabled = sheetState.isVisible) {
        coroutineScope.launch {
            sheetState.hide()
        }
    }
}