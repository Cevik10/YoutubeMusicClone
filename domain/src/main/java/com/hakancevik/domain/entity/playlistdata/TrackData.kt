package com.hakancevik.domain.entity.playlistdata

data class TrackData(
    val id: String,
    val title: String,
    val duration: String,
    val artist: ArtistData,
    val album: AlbumData
)