package com.hakancevik.youtubemusicclone.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hakancevik.domain.entity.ResultData
import com.hakancevik.youtubemusicclone.R
import com.hakancevik.youtubemusicclone.common.theme.AppTheme
import com.hakancevik.youtubemusicclone.common.widgets.LoadingScreen
import com.hakancevik.youtubemusicclone.ui.home.components.CategoriesWidget
import com.hakancevik.youtubemusicclone.ui.home.components.HomeAppBar
import com.hakancevik.youtubemusicclone.ui.home.components.SongSection
import com.hakancevik.youtubemusicclone.ui.home.components.SongsGrid

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    AppTheme {
        HomeContent(viewModel)
    }
}

@Composable
fun HomeContent(viewModel: HomeViewModel) {

    val playlistState by viewModel.playlist.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        HomeAppBar(
            onPlayClick = { },
            onNotificationsClick = { },
            onSearchClick = { },
            onAccountClick = { }
        )

        when (playlistState) {
            is ResultData.Loading -> {
                LoadingScreen()
            }

            is ResultData.Success -> {
                val playlist = (playlistState as ResultData.Success).data
                // Verileri burada gösterebilirsiniz.
//                Text(text = "Title: ${playlist.title}")
//                Text(text = "Description: ${playlist.description}")
//                Text(text = "Number of Tracks: ${playlist.tracks.data}")

                LazyColumn {
                    items(playlist.tracks.data) { track ->
                        Text(text = track.title)
                    }
                }
            }

            is ResultData.Error -> {
                Text(text = "Error: ${(playlistState as ResultData.Error).exception.message}")
            }
        }


    }
}

//@Composable
//fun HomeContent(viewModel: HomeViewModel) {
//
//    Column(modifier = Modifier.fillMaxSize()) {
//        HomeAppBar(
//            onPlayClick = { },
//            onNotificationsClick = { },
//            onSearchClick = { },
//            onAccountClick = { }
//        )
//
//        CategoriesWidget()
//    }
//}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(navController = rememberNavController())
    }
}





