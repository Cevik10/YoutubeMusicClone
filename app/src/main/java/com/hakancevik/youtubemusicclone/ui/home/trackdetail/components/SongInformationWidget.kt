package com.hakancevik.youtubemusicclone.ui.home.trackdetail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hakancevik.domain.entity.trackdata.TrackData
import com.hakancevik.youtubemusicclone.R


@Composable
fun SongInformationWidget(track: TrackData) {
    Row(Modifier.padding(start = 16.dp)) {
        Column(modifier = Modifier.weight(1f)) {
            track.title?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(end = 12.dp),
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
            Text(
                text = track.artist?.name ?: "",
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(end = 12.dp, top = 6.dp),
                color = Color.LightGray
            )
        }
        Icon(
            imageVector = Icons.Filled.AddCircleOutline,
            contentDescription = stringResource(id = R.string.cd_add_to_playlist),
            tint = Color.White,
            modifier = Modifier
                .size(50.dp)
                .padding(end = 12.dp)
                .align(Alignment.CenterVertically)
        )
    }
}
