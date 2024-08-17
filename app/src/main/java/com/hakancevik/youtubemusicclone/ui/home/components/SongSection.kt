package com.hakancevik.youtubemusicclone.ui.home.components

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakancevik.youtubemusicclone.R
import com.hakancevik.youtubemusicclone.common.theme.AppTheme

@Composable
fun SongSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    onPlayAllClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .paddingFromBaseline(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )

            androidx.compose.material3.OutlinedButton(
                onClick = onPlayAllClick, // Handle the click action
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .shadow(shape = MaterialTheme.shapes.large, elevation = 8.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.onPrimary)
            ) {
                Text(text = "Play all", style = MaterialTheme.typography.titleSmall)
            }
        }

        Spacer(modifier = Modifier.size(4.dp))
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun SongSectionPreview() {
    SongSection(title = R.string.song_section_title) {
        SongsGrid()
    }

}



