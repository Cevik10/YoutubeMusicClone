package com.hakancevik.youtubemusicclone.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakancevik.domain.entity.playlistdata.TrackData
import com.hakancevik.youtubemusicclone.common.theme.AppTheme

@Composable
fun TracksGrid(
    tracks: List<TrackData>,
    modifier: Modifier = Modifier,
    onTrackClick: (TrackData) -> Unit = {} // Added parameter for click handling
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(4),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.height((168 * 2 - 24).dp)
    ) {
        items(tracks) { trackData ->
            TrackCard(
                track = trackData,
                onClick = { onTrackClick(trackData) } // Pass the click action
            )
        }
    }
}

//@Preview
//@Composable
//fun SongsGridPreview() {
//    AppTheme {
//        //SongsGrid()
//    }
//}