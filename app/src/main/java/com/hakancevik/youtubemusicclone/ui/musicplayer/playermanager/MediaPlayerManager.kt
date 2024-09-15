package com.hakancevik.youtubemusicclone.ui.musicplayer.playermanager

import android.media.MediaPlayer
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import com.hakancevik.domain.entity.MediaPlayerState

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

    val duration: Int?
        get() = mediaPlayer?.duration

    init {
        startUpdatingCurrentPosition()
    }

    fun initializeMediaPlayer(mediaSession: MediaSessionCompat?, url: String) {
        mediaPlayer?.release() // Release any existing player to avoid conflicts
        mediaPlayer = MediaPlayer().apply {
            setDataSource(url) // Use dynamic URL here
            prepare()
            setOnCompletionListener {
                pausePlayback()
            }
        }
        this.mediaSession = mediaSession
    }

    override fun startPlayback() {
        mediaPlayer?.start()
        _playerState.value = MediaPlayerState.PLAYING
    }

    override fun pausePlayback() {
        mediaPlayer?.pause()
        _playerState.value = MediaPlayerState.PAUSED
    }

    override fun stopPlayback() {
        mediaPlayer?.stop()
        _playerState.value = MediaPlayerState.STOPPED
    }

    override fun releasePlayback() {
        mediaPlayer?.release()
    }

    override fun seekTo(position: Long) {
        mediaPlayer?.seekTo(position.toInt())
        _currentPosition.value = position.toInt()
    }

    private fun startUpdatingCurrentPosition() {
        CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                delay(1000)
                mediaPlayer?.let {
                    val currentPosition = it.currentPosition
                    this@MediaPlayerManager._currentPosition.value = currentPosition
                    updatePlaybackState()
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

        val mPlaybackState = PlaybackStateCompat.Builder().setState(state, position, playbackSpeed).setActions(
            PlaybackStateCompat.ACTION_PLAY or PlaybackStateCompat.ACTION_PAUSE or PlaybackStateCompat.ACTION_SKIP_TO_NEXT or PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS or PlaybackStateCompat.ACTION_SEEK_TO
        ).build()

        mediaSession?.setPlaybackState(mPlaybackState)
    }
}


//
//class MediaPlayerManager : PlaybackControl, PlaybackInteraction {
//    private var mediaPlayer: MediaPlayer? = null
//    private var mediaSession: MediaSessionCompat? = null
//    private val _playerState = MutableStateFlow(MediaPlayerState.STOPPED)
//    val playerState: StateFlow<MediaPlayerState> = _playerState
//
//    private val _currentPosition = MutableStateFlow(0)
//    val currentPosition: StateFlow<Int> = _currentPosition
//
//    val duration: Int?
//        get() = mediaPlayer?.duration
//
//    init {
//        startUpdatingCurrentPosition()
//    }
//
//    fun initializeMediaPlayer(mediaSession: MediaSessionCompat?) {
//        this.mediaSession = mediaSession
//    }
//
//    fun startPlayback(trackUrl: String) {
//        mediaPlayer?.release() // Release any existing media player
//        mediaPlayer = MediaPlayer().apply {
//            setDataSource(trackUrl)
//            prepare()
//            setOnCompletionListener {
//                pausePlayback()
//            }
//            start()
//            _playerState.value = MediaPlayerState.PLAYING
//        }
//        updatePlaybackState()
//    }
//
//    override fun startPlayback() {
//        mediaPlayer?.start()
//        _playerState.value = MediaPlayerState.PLAYING
//        updatePlaybackState()
//    }
//
//    override fun pausePlayback() {
//        mediaPlayer?.pause()
//        _playerState.value = MediaPlayerState.PAUSED
//        updatePlaybackState()
//    }
//
//    override fun stopPlayback() {
//        mediaPlayer?.stop()
//        _playerState.value = MediaPlayerState.STOPPED
//        updatePlaybackState()
//    }
//
//    override fun releasePlayback() {
//        mediaPlayer?.release()
//        mediaPlayer = null
//    }
//
//    override fun seekTo(position: Long) {
//        mediaPlayer?.seekTo(position.toInt())
//        _currentPosition.value = position.toInt()
//        updatePlaybackState()
//    }
//
//    private fun startUpdatingCurrentPosition() {
//        CoroutineScope(Dispatchers.IO).launch {
//            while (isActive) {
//                delay(1000)
//                mediaPlayer?.let {
//                    val currentPosition = it.currentPosition
//                    this@MediaPlayerManager._currentPosition.value = currentPosition
//                    updatePlaybackState()
//                }
//            }
//        }
//    }
//
//    override fun updatePlaybackState() {
//        val state = if (mediaPlayer?.isPlaying == true) {
//            PlaybackStateCompat.STATE_PLAYING
//        } else {
//            PlaybackStateCompat.STATE_PAUSED
//        }
//
//        val position = mediaPlayer?.currentPosition?.toLong() ?: 0L
//        val playbackSpeed = if (mediaPlayer?.isPlaying == true) 1.0f else 0f
//
//        val playbackState = PlaybackStateCompat.Builder()
//            .setState(state, position, playbackSpeed)
//            .setActions(
//                PlaybackStateCompat.ACTION_PLAY or
//                        PlaybackStateCompat.ACTION_PAUSE or
//                        PlaybackStateCompat.ACTION_SKIP_TO_NEXT or
//                        PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS or
//                        PlaybackStateCompat.ACTION_SEEK_TO
//            )
//            .build()
//
//        mediaSession?.setPlaybackState(playbackState)
//    }
//}

