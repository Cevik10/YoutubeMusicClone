package com.hakancevik.youtubemusicclone.ui.home.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakancevik.youtubemusicclone.R
import com.hakancevik.youtubemusicclone.common.widgets.DynamicAsyncImage

@Composable
fun MusicPlayerBottomWidget(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    singer: String,

    onPlayPauseClicked: (action: String) -> Unit
) {


    Box(modifier = modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)) {
        Row(modifier = Modifier.fillMaxSize()) {
            DynamicAsyncImage(
                imageUrl = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(text = title, color = Color.White)
                Text(text = singer, color = Color.LightGray)
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Filled.Pause,
                contentDescription = stringResource(id = R.string.cd_play_pause),
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterVertically)
                    .clickable {

                    },
                tint = Color.White,
            )
        }
        LinearProgressIndicator(
            progress = { 2.3f },
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .padding(horizontal = 8.dp)
                .align(Alignment.BottomCenter),
            color = Color.White,
            trackColor = Color.DarkGray,
        )
    }
}

@Preview()
@Composable
fun MusicPlayerBottomWidgetPreview() {
    MusicPlayerBottomWidget(
        imageUrl = "",
        title = "title",
        singer = "singer",
        onPlayPauseClicked = { action -> }
    )
}