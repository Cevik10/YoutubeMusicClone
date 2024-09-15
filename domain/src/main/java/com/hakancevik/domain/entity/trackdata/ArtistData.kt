package com.hakancevik.domain.entity.trackdata


data class ArtistData(
    val id: String,
    val link: String?,
    val name: String,
    val pictureUrl: String?,  // Nullable
    val pictureBigUrl: String?,
    val pictureMediumUrl: String?,
    val pictureSmallUrl: String?,
    val pictureXlUrl: String?,
    val isRadio: Boolean,
    val shareUrl: String?,
    val tracklistUrl: String?,
    val type: String?
)