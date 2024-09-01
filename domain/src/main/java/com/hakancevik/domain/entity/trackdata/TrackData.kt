package com.hakancevik.domain.entity.trackdata

data class TrackData(
    val album: AlbumData?,
    val artist: ArtistData?,
    val availableCountries: List<String>?,
    val beatsPerMinute: Double?,
    val contributors: List<ContributorData>?,
    val diskNumber: Int?,
    val duration: String?,
    val explicitContentCover: Int?,
    val explicitContentLyrics: Int?,
    val isExplicit: Boolean?,
    val gain: Double?,
    val id: String?,
    val isrc: String?,
    val link: String?,
    val imageHash: String?,
    val previewUrl: String?,
    val rank: String?,
    val isReadable: Boolean?,
    val releaseDate: String?,
    val shareUrl: String?,
    val title: String?,
    val shortTitle: String?,
    val position: Int?,
    val token: String?,
    val type: String?
)