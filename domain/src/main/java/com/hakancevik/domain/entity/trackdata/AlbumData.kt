package com.hakancevik.domain.entity.trackdata

data class AlbumData(
    val coverUrl: String?,  // Make coverUrl nullable
    val coverBigUrl: String?,
    val coverMediumUrl: String?,
    val coverSmallUrl: String?,
    val coverXlUrl: String?,
    val id: String,
    val link: String?,
    val md5Image: String?,
    val releaseDate: String?,
    val title: String?,
    val tracklistUrl: String?,
    val type: String?
)