package com.hakancevik.data.model.track

import com.google.gson.annotations.SerializedName
import com.hakancevik.domain.entity.trackdata.AlbumData
import com.hakancevik.domain.entity.trackdata.ArtistData
import com.hakancevik.domain.entity.trackdata.ContributorData

data class TrackDTO(
    @SerializedName("album")
    val albumData: AlbumDTO,

    @SerializedName("artist")
    val artistData: ArtistDTO,

    @SerializedName("available_countries")
    val availableCountries: List<String>,

    @SerializedName("bpm")
    val beatsPerMinute: Double,

    @SerializedName("contributor")
    val contributorData: List<ContributorDTO>,

    @SerializedName("disk_number")
    val diskNumber: Int,

    @SerializedName("duration")
    val duration: String,

    @SerializedName("explicit_content_cover")
    val explicitContentCover: Int,

    @SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Int,

    @SerializedName("explicit_lyrics")
    val isExplicit: Boolean,

    @SerializedName("gain")
    val gain: Double,

    @SerializedName("id")
    val id: String,

    @SerializedName("isrc")
    val isrc: String,

    @SerializedName("link")
    val link: String,

    @SerializedName("md5_image")
    val imageHash: String,

    @SerializedName("preview")
    val preview: String,

    @SerializedName("rank")
    val rank: String,

    @SerializedName("readable")
    val isReadable: Boolean,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("share")
    val share: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("title_short")
    val shortTitle: String,

    @SerializedName("track_position")
    val trackPosition: Int,

    @SerializedName("track_token")
    val trackToken: String,

    @SerializedName("type")
    val type: String
)