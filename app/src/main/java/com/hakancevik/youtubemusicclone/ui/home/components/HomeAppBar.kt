package com.hakancevik.youtubemusicclone.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakancevik.youtubemusicclone.R

@Composable
fun HomeAppBar(
    onPlayClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    onSearchClick: () -> Unit,
    onAccountClick: () -> Unit
) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        IconButton(onClick = onPlayClick) {
            Icon(
                painter = painterResource(id = R.drawable.ym_logo),
                contentDescription = stringResource(R.string.cd_app_logo),
                tint = Color.Unspecified,  // Prevents the icon from being tinted
                modifier = Modifier.size(24.dp)  // Set the size of the icon (adjust the size as needed)
            )
        }
        Text(text = "Music", modifier = Modifier.align(Alignment.CenterVertically), color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.weight(1f))

        IconButton(onClick = onNotificationsClick) {
            Icon(
                imageVector = Icons.Default.NotificationsNone,
                contentDescription = stringResource(R.string.cd_notifications),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        IconButton(onClick = onSearchClick) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.cd_search),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        IconButton(onClick = onAccountClick) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = stringResource(R.string.cd_account),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun HomeAppBarPreview() {
    HomeAppBar(
        onPlayClick = { /* Play Button Clicked */ },
        onNotificationsClick = { /* Notifications Button Clicked */ },
        onSearchClick = { /* Search Button Clicked */ },
        onAccountClick = { /* Account Button Clicked */ }
    )
}