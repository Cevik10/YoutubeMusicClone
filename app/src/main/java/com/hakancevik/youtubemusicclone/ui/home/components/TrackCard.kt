package com.hakancevik.youtubemusicclone.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakancevik.domain.entity.playlistdata.AlbumData
import com.hakancevik.domain.entity.playlistdata.ArtistData
import com.hakancevik.domain.entity.playlistdata.TrackData
import com.hakancevik.youtubemusicclone.R
import com.hakancevik.youtubemusicclone.common.theme.AppTheme
import com.hakancevik.youtubemusicclone.common.widgets.DynamicAsyncImage

@Composable
fun TrackCard(
    track: TrackData,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {} // Added parameter for click handling
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier
            .clickable { onClick() } // Handle click action
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(325.dp)
        ) {

            DynamicAsyncImage(
                imageUrl = track.album.coverMedium,
                contentDescription = "",
                isCircular = false,
                modifier = Modifier
                    .size(74.dp)
                    .padding(4.dp)
                    .clip(MaterialTheme.shapes.small)
            )

            Column(
                modifier = Modifier
                    .height(74.dp)
                    .weight(1f)
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = track.title,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = track.artist.name,
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            IconButton(
                onClick = { },
                modifier = Modifier.padding(end = 0.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = Color.Unspecified,
                    contentColor = Color.Unspecified
                )
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = stringResource(id = R.string.options_button),
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview
@Composable
fun TrackCardPreview() {
    AppTheme {
        TrackCard(TrackData("1", "title", "artist", ArtistData("1", "John kennedy"), AlbumData("1", "album title", "https://e-cdns-images.dzcdn.net/images/cover/c65b3bd84e81056e060be144381c06c8/250x250-000000-80-0-0.jpg")))
    }

}



