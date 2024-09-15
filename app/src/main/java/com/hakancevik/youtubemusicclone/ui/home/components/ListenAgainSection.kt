package com.hakancevik.youtubemusicclone.ui.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakancevik.youtubemusicclone.R
import com.hakancevik.youtubemusicclone.common.theme.AppTheme

@Composable
fun ListenAgainSection(
    title: String,
    @DrawableRes userProfileImageDrawable: Int,
    userName: String,
    modifier: Modifier = Modifier,
    onListenAgainClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Row(
            modifier = Modifier
                .clickable { onListenAgainClick() }
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .paddingFromBaseline(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(userProfileImageDrawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    modifier = Modifier.padding(start = 8.dp),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, color = Color.Gray)
                )

                Text(
                    text = userName,
                    modifier = Modifier.padding(start = 8.dp),
                    style = MaterialTheme.typography.titleSmall
                )
            }



            OutlinedIconButton(
                onClick = onListenAgainClick, // Handle the click action
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .shadow(shape = MaterialTheme.shapes.large, elevation = 8.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.onPrimary)
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "Arrow Right"
                )
            }
        }

        Spacer(modifier = Modifier.size(4.dp))
        content()
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewListenAgainSection() {
    AppTheme {
        ListenAgainSection(
            title = "Listen Again",
            userProfileImageDrawable = R.drawable.ym_logo, // Replace with an actual drawable resource
            userName = "John Doe",
            modifier = Modifier.padding(16.dp),
            onListenAgainClick = { /* Handle click */ }
        ) {
            // Sample content composable
            BasicText(text = "This is a sample listen again section content.")
        }
    }
}

