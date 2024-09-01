package com.hakancevik.youtubemusicclone.ui.musicplayer.playermanager

interface PlaybackInteraction {
    fun seekTo(position: Long)
    fun updatePlaybackState()
}
