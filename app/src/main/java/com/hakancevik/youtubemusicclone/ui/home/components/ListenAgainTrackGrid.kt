package com.hakancevik.youtubemusicclone.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hakancevik.domain.entity.playlistdata.TrackData


@Composable
fun ListenAgainTrackGrid(
    tracks: List<TrackData>,
    modifier: Modifier = Modifier,
    onTrackClick: (TrackData) -> Unit = {} // Added parameter for click handling
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height((130 * 2).dp)
    ) {
        items(tracks) { trackData ->
            ListenAgainTrackItem(
                track = trackData,
                onClick = { onTrackClick(trackData) } // Pass the click action
            )
        }
    }
}