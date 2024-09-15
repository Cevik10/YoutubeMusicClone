package com.hakancevik.youtubemusicclone.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakancevik.youtubemusicclone.common.theme.AppTheme
import com.hakancevik.youtubemusicclone.common.widgets.DynamicAsyncImage
import com.hakancevik.youtubemusicclone.ui.home.createDummyTrack


@Composable
fun ListenAgainTrackItem(
    track: com.hakancevik.domain.entity.playlistdata.TrackData,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .clickable { onClick() }
            .width(100.dp)
            .wrapContentHeight()
    ) {

        Column() {

            Box {
                DynamicAsyncImage(
                    imageUrl = track.album.coverMedium,
                    contentDescription = "",
                    isCircular = false,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(MaterialTheme.shapes.small)
                )
            }

            Text(
                text = track.artist.name,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 4.dp, end = 4.dp)
            )


        }

    }
}


@Preview
@Composable
fun ListenAgainTrackItemPreview() {
    AppTheme {
        //ListenAgainTrackItem(track = createDummyTrack(), onClick = {})
    }
}