package com.hakancevik.youtubemusicclone.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakancevik.domain.entity.ResultData
import com.hakancevik.domain.entity.playlistdata.PlaylistData
import com.hakancevik.domain.usecase.GetPlaylistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPlaylistUseCase: GetPlaylistUseCase
) : ViewModel() {

    private val _playlistState = MutableStateFlow<PlaylistUiState>(PlaylistUiState.Loading)
    val playlistState: StateFlow<PlaylistUiState> = _playlistState

    private val _listenAgainTracksState = MutableStateFlow<ListenAgainTracksUiState>(ListenAgainTracksUiState.Loading)
    val listenAgainTracksState: StateFlow<ListenAgainTracksUiState> = _listenAgainTracksState

    init {
        fetchPlaylist("908622995")
        fetchListenAgainTracks("908622995")
    }

    private fun fetchPlaylist(id: String) {
        viewModelScope.launch {
            getPlaylistUseCase(id).collect { result ->
                _playlistState.value = when (result) {
                    is ResultData.Loading -> PlaylistUiState.Loading
                    is ResultData.Success -> PlaylistUiState.Success(result.data)
                    is ResultData.Error -> PlaylistUiState.Error(result.exception)
                }
            }
        }
    }

    private fun fetchListenAgainTracks(id: String) {
        viewModelScope.launch {
            getPlaylistUseCase(id).collect { result ->
                _listenAgainTracksState.value = when (result) {
                    is ResultData.Loading -> ListenAgainTracksUiState.Loading
                    is ResultData.Success -> ListenAgainTracksUiState.Success(result.data)
                    is ResultData.Error -> ListenAgainTracksUiState.Error(result.exception)
                }
            }
        }
    }
}


sealed interface PlaylistUiState {
    data object Loading : PlaylistUiState
    data class Success(val playlist: PlaylistData) : PlaylistUiState
    data class Error(val exception: Throwable) : PlaylistUiState
}

sealed interface ListenAgainTracksUiState {
    data object Loading : ListenAgainTracksUiState
    data class Success(val tracks: PlaylistData) : ListenAgainTracksUiState
    data class Error(val exception: Throwable) : ListenAgainTracksUiState
}