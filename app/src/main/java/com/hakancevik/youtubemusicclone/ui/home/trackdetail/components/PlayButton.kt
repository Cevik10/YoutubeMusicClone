package com.hakancevik.youtubemusicclone.ui.home.trackdetail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakancevik.youtubemusicclone.common.theme.AppTheme


@Composable
private fun PlayButton(onClick: () -> Unit) {
    Icon(
        imageVector = Icons.Filled.PlayCircle,
        tint = Color.White,
        modifier = Modifier
            .size(50.dp)
            .testTag("PlayIcon")
            .clickable { onClick() },
        contentDescription = null
    )
}

@Preview(device = "id:Nexus 10")
@Composable
fun PlayButtonPreview() {
    AppTheme {
        PlayButton({ })
    }
}