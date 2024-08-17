package com.hakancevik.data.model

data class Song(
    val id: String,
    val title: String,
    val artist: String,
    val album: String,
    val coverUrl: String,  // URL of the album cover image
    val duration: Int  // Duration in seconds
)