package com.hakancevik.youtubemusicclone

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.hakancevik.youtubemusicclone.ui.musicplayer.service.MusicPlayerService
import com.hakancevik.youtubemusicclone.ui.musicplayer.service.MusicServiceConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val musicServiceConnection: MusicServiceConnection,
    private val application: Application,
) : ViewModel() {

    val serviceBinder: MutableState<MusicPlayerService.MusicServiceBinder?> = musicServiceConnection.serviceBinder

    override fun onCleared() {
        super.onCleared()
        musicServiceConnection.unbindService(application)
    }
}
