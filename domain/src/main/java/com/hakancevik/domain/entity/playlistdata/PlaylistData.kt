package com.hakancevik.domain.entity.playlistdata

data class PlaylistData(
    val id: String,
    val title: String,
    val description: String,
    val creator: CreatorData,
    val duration: Int,
    val fans: Int,
    val link: String,
    val pictureMedium: String,  // Sadece orta boyutlu resim örneğini kullanıyorum
    val tracks: TracksData
)