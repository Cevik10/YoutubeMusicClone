package com.hakancevik.youtubemusicclone.ui.musicplayer.playermanager

import android.media.MediaPlayer
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import com.hakancevik.domain.entity.MediaPlayerState

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MediaPlayerManager : PlaybackControl, PlaybackInteraction {
    private var mediaPlayer: MediaPlayer? = null
    private var mediaSession: MediaSessionCompat? = null
    private val _playerState = MutableStateFlow(MediaPlayerState.STOPPED)
    val playerState: StateFlow<MediaPlayerState> = _playerState
    private val _currentPosition = MutableStateFlow(0)
    val currentPosition: StateFlow<Int> = _currentPosition
    private var updatePositionJob: Job? = null // Track the coroutine job

    val duration: Int?
        get() = mediaPlayer?.duration

    fun initializeMediaPlayer(mediaSession: MediaSessionCompat?, url: String) {
        // Cancel any existing position update coroutine
        updatePositionJob?.cancel()

        mediaPlayer?.release() // Release any existing player to avoid conflicts
        mediaPlayer = MediaPlayer().apply {
            setDataSource(url)
            prepare()
            setOnCompletionListener {
                pausePlayback()
            }
        }
        this.mediaSession = mediaSession

        // Start updating position only after mediaPlayer is initialized
        startUpdatingCurrentPosition()
    }

    override fun startPlayback() {
        mediaPlayer?.takeIf { it.isPrepared() }?.start()
        _playerState.value = MediaPlayerState.PLAYING
    }

    override fun pausePlayback() {
        mediaPlayer?.takeIf { it.isPlaying }?.pause()
        _playerState.value = MediaPlayerState.PAUSED
    }

    override fun stopPlayback() {
        mediaPlayer?.stop()
        _playerState.value = MediaPlayerState.STOPPED
    }

    override fun releasePlayback() {
        updatePositionJob?.cancel() // Cancel the coroutine
        mediaPlayer?.release()
        mediaPlayer = null
        _playerState.value = MediaPlayerState.STOPPED
    }

    override fun seekTo(position: Long) {
        mediaPlayer?.takeIf { it.isPrepared() }?.seekTo(position.toInt())
        _currentPosition.value = position.toInt()
    }

    private fun startUpdatingCurrentPosition() {
        updatePositionJob = CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                delay(1000)
                try {
                    mediaPlayer?.takeIf { it.isPrepared() }?.let {
                        val currentPosition = it.currentPosition
                        this@MediaPlayerManager._currentPosition.value = currentPosition
                        updatePlaybackState()
                    }
                } catch (e: IllegalStateException) {
                    // Handle the exception (e.g., log it or stop the coroutine)
                    _playerState.value = MediaPlayerState.STOPPED
                    cancel() // Stop the coroutine if mediaPlayer is in an invalid state
                }
            }
        }
    }

    override fun updatePlaybackState() {
        val state = if (mediaPlayer?.isPlaying == true) {
            PlaybackStateCompat.STATE_PLAYING
        } else {
            PlaybackStateCompat.STATE_PAUSED
        }

        val position = mediaPlayer?.currentPosition?.toLong() ?: 0L
        val playbackSpeed = if (mediaPlayer?.isPlaying == true) 1.0f else 0f

        val mPlaybackState = PlaybackStateCompat.Builder()
            .setState(state, position, playbackSpeed)
            .setActions(
                PlaybackStateCompat.ACTION_PLAY or
                        PlaybackStateCompat.ACTION_PAUSE or
                        PlaybackStateCompat.ACTION_SKIP_TO_NEXT or
                        PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS or
                        PlaybackStateCompat.ACTION_SEEK_TO
            ).build()

        mediaSession?.setPlaybackState(mPlaybackState)
    }

    // Helper function to check if MediaPlayer is in a valid state
    private fun MediaPlayer.isPrepared(): Boolean {
        return try {
            duration // Accessing duration to check if the player is prepared
            true
        } catch (e: IllegalStateException) {
            false
        }
    }
}