package com.hakancevik.youtubemusicclone.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hakancevik.youtubemusicclone.R
import com.hakancevik.youtubemusicclone.common.theme.AppTheme
import com.hakancevik.youtubemusicclone.ui.home.components.CategoriesWidget
import com.hakancevik.youtubemusicclone.ui.home.components.HomeAppBar
import com.hakancevik.youtubemusicclone.ui.home.components.SongSection
import com.hakancevik.youtubemusicclone.ui.home.components.SongsGrid

@Composable
fun HomeScreen(navController: NavController) {
    AppTheme {
        HomeContent()
    }
}


@Composable
fun HomeContent() {

    Column(modifier = Modifier.fillMaxSize()) {
        HomeAppBar(
            onPlayClick = { },
            onNotificationsClick = { },
            onSearchClick = { },
            onAccountClick = { }
        )

        CategoriesWidget()

        SongSection(title = R.string.song_section_title) {
            SongsGrid(onSongClick = { song -> println(song.artist) })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(navController = rememberNavController())
    }
}





