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

    private val _playlist = MutableStateFlow<ResultData<PlaylistData>>(ResultData.Loading())
    val playlist: StateFlow<ResultData<PlaylistData>> = _playlist

    init {
        fetchPlaylist("908622995")
    }

    fun fetchPlaylist(id: String) {
        viewModelScope.launch {
            getPlaylistUseCase(id).collect {
                _playlist.value = it
            }
        }
    }
}