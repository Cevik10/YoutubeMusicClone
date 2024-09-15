package com.hakancevik.youtubemusicclone.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hakancevik.domain.entity.ResultData
import com.hakancevik.domain.entity.trackdata.AlbumData
import com.hakancevik.domain.entity.trackdata.ArtistData
import com.hakancevik.domain.entity.trackdata.ContributorData
import com.hakancevik.domain.entity.trackdata.TrackData
import com.hakancevik.youtubemusicclone.R
import com.hakancevik.youtubemusicclone.common.Constants.DEFAULT_VALUE
import com.hakancevik.youtubemusicclone.common.theme.AppTheme
import com.hakancevik.youtubemusicclone.common.widgets.ErrorScreen
import com.hakancevik.youtubemusicclone.common.widgets.LoadingScreen
import com.hakancevik.youtubemusicclone.common.widgets.Screen
import com.hakancevik.youtubemusicclone.ui.home.components.CategoriesWidget
import com.hakancevik.youtubemusicclone.ui.home.components.HomeAppBar
import com.hakancevik.youtubemusicclone.ui.home.components.ListenAgainSection
import com.hakancevik.youtubemusicclone.ui.home.components.ListenAgainTrackGrid
import com.hakancevik.youtubemusicclone.ui.home.trackdetail.MusicPlayerDetailBottomSheet
import com.hakancevik.youtubemusicclone.ui.musicplayer.service.MusicPlayerService

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    serviceBinder: MusicPlayerService.MusicServiceBinder?,
    bottomSheetWidgetBounds: MutableState<Float?>,
) {
    AppTheme {
        HomeContent(navController = navController, viewModel = viewModel, serviceBinder = serviceBinder, bottomSheetWidgetBounds = bottomSheetWidgetBounds)
    }
}

@Composable
fun HomeContent(
    navController: NavController,
    viewModel: HomeViewModel,
    serviceBinder: MusicPlayerService.MusicServiceBinder?,
    bottomSheetWidgetBounds: MutableState<Float?>,
) {
    val playlistState by viewModel.playlistState.collectAsState()
    val listenAgainTracksState by viewModel.listenAgainTracksState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color.Red.copy(alpha = 0.5f),
                        Color.Yellow.copy(alpha = 0.5f),
                        Color.Green.copy(alpha = 0.5f),
                        Color.Blue.copy(alpha = 0.5f),
                        MaterialTheme.colorScheme.background
                    ),
                    center = Offset(0f, 0f),
                    radius = 1000f
                )
            )
    ) {
        when {
            playlistState is PlaylistUiState.Loading || listenAgainTracksState is ListenAgainTracksUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LoadingScreen()
                }
            }

            playlistState is PlaylistUiState.Error || listenAgainTracksState is ListenAgainTracksUiState.Error -> {
                val errorMessage = (playlistState as? PlaylistUiState.Error)?.exception?.message
                    ?: (listenAgainTracksState as? ListenAgainTracksUiState.Error)?.exception?.message
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    ErrorScreen(message = "Error: $errorMessage")
                }
            }

            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item { HomeAppBar(onPlayClick = {}, onAccountClick = {}, onSearchClick = {}, onNotificationsClick = {}) }

                    if (playlistState is PlaylistUiState.Success) {
                        val playlist = (playlistState as PlaylistUiState.Success).playlist
                        item {
                            CategoriesWidget(
                                tracks = playlist.tracks.data,
                                playlistTitle = playlist.title,
                                onTrackClick = { trackData ->
                                    navController.navigate(Screen.TrackDetail.createRoute(trackData.id))
                                }
                            )
                            Spacer(modifier = Modifier.size(16.dp))
                        }
                    }

                    if (listenAgainTracksState is ListenAgainTracksUiState.Success) {
                        val listenAgainList = (listenAgainTracksState as ListenAgainTracksUiState.Success).tracks
                        item {
                            ListenAgainSection(
                                title = "Listen Again",
                                userProfileImageDrawable = R.drawable.ym_logo,
                                userName = "Hakan Cevik"
                            ) {
                                ListenAgainTrackGrid(tracks = listenAgainList.tracks.data)
                            }
                        }
                    }

                    item { Spacer(modifier = Modifier.size(73.dp)) }
                }

                MusicPlayerDetailBottomSheet(
                    serviceBinder = serviceBinder,
                    bottomSheetWidgetBounds = bottomSheetWidgetBounds,
                    modifier = Modifier.align(Alignment.BottomCenter),
                    trackData = createDummyTrack()
                )
            }
        }
    }
}


fun createDummyTrack(): TrackData {
    val dummyAlbumData = AlbumData(
        coverUrl = "https://example.com/cover.jpg",
        coverBigUrl = "https://example.com/cover_big.jpg",
        coverMediumUrl = "https://example.com/cover_medium.jpg",
        coverSmallUrl = "https://example.com/cover_small.jpg",
        coverXlUrl = "https://example.com/cover_xl.jpg",
        id = "album123",
        link = "https://example.com/album",
        md5Image = "someMd5Hash",
        releaseDate = "2023-09-01",
        title = "Dummy Album",
        tracklistUrl = "https://example.com/tracklist",
        type = "album"
    )

    val dummyArtistData = ArtistData(
        id = "artist123",
        name = "Dummy Track, This is a dummy track.",
        link = "https://example.com/artist",
        pictureUrl = "https://example.com/artist_picture.jpg",
        pictureBigUrl = "https://example.com/artist_picture_big.jpg",
        pictureMediumUrl = "https://example.com/artist_picture_medium.jpg",
        pictureSmallUrl = "https://example.com/artist_picture_small.jpg",
        pictureXlUrl = "https://example.com/artist_picture_xl.jpg",
        isRadio = true,
        shareUrl = "https://example.com/artist_share",
        tracklistUrl = "https://example.com/artist_tracklist",
        type = "artist"
    )

    val dummyContributors = listOf(
        ContributorData(
            id = 1,
            name = "Contributor One",
            link = "https://example.com/contrib1",
            pictureUrl = "https://example.com/contrib1_picture.jpg",
            pictureBigUrl = "https://example.com/contrib1_picture_big.jpg",
            pictureMediumUrl = "https://example.com/contrib1_picture_medium.jpg",
            pictureSmallUrl = "https://example.com/contrib1_picture_small.jpg",
            pictureXlUrl = "https://example.com/contrib1_picture_xl.jpg",
            isRadio = false,
            role = "Producer",
            shareUrl = "https://example.com/contrib1_share",
            tracklistUrl = "https://example.com/contrib1_tracklist",
            type = "contributor"
        ),
        ContributorData(
            id = 2,
            name = "Contributor Two",
            link = "https://example.com/contrib2",
            pictureUrl = "https://example.com/contrib2_picture.jpg",
            pictureBigUrl = "https://example.com/contrib2_picture_big.jpg",
            pictureMediumUrl = "https://example.com/contrib2_picture_medium.jpg",
            pictureSmallUrl = "https://example.com/contrib2_picture_small.jpg",
            pictureXlUrl = "https://example.com/contrib2_picture_xl.jpg",
            isRadio = false,
            role = "Writer",
            shareUrl = "https://example.com/contrib2_share",
            tracklistUrl = "https://example.com/contrib2_tracklist",
            type = "contributor"
        )
    )

    return TrackData(
        album = dummyAlbumData,
        artist = dummyArtistData,
        availableCountries = listOf("US", "UK", "CA"),
        beatsPerMinute = 120.0,
        contributors = dummyContributors,
        diskNumber = 1,
        duration = "03:45",
        explicitContentCover = 1,
        explicitContentLyrics = 1,
        isExplicit = true,
        gain = 0.5,
        id = "track123",
        isrc = "US1234567890",
        link = "https://example.com/track",
        imageHash = "hash123",
        previewUrl = "https://example.com/preview.mp3",
        rank = "1",
        isReadable = true,
        releaseDate = "2023-09-01",
        shareUrl = "https://example.com/share",
        title = "Dummy Track",
        shortTitle = "Dummy",
        position = 1,
        token = null,
        type = "track"

    )
}


