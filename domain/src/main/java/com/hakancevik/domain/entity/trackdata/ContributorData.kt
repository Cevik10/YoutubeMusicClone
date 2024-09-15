package com.hakancevik.domain.entity.trackdata

data class ContributorData(
    val id: Int,
    val link: String?,
    val name: String,
    val pictureUrl: String?,
    val pictureBigUrl: String?,
    val pictureMediumUrl: String?,
    val pictureSmallUrl: String?,
    val pictureXlUrl: String?,
    val isRadio: Boolean,
    val role: String?,
    val shareUrl: String?,
    val tracklistUrl: String?,
    val type: String?
)
