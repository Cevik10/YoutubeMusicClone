package com.hakancevik.data.model.playlist

import com.google.gson.annotations.SerializedName

data class Playlist(
    @SerializedName("checksum") val checksum: String,
    @SerializedName("collaborative") val collaborative: Boolean,
    @SerializedName("creation_date") val creationDate: String,
    @SerializedName("creator") val creator: Creator,
    @SerializedName("description") val description: String,
    @SerializedName("duration") val duration: Int,
    @SerializedName("fans") val fans: Int,
    @SerializedName("id") val id: String,
    @SerializedName("is_loved_track") val isLovedTrack: Boolean,
    @SerializedName("link") val link: String,
    @SerializedName("md5_image") val md5Image: String,
    @SerializedName("nb_tracks") val nbTracks: Int,
    @SerializedName("picture") val picture: String,
    @SerializedName("picture_big") val pictureBig: String,
    @SerializedName("picture_medium") val pictureMedium: String,
    @SerializedName("picture_small") val pictureSmall: String,
    @SerializedName("picture_type") val pictureType: String,
    @SerializedName("picture_xl") val pictureXl: String,
    @SerializedName("public") val isPublic: Boolean,
    @SerializedName("share") val share: String,
    @SerializedName("title") val title: String,
    @SerializedName("tracklist") val tracklist: String,
    @SerializedName("tracks") val tracks: Tracks,
    @SerializedName("type") val type: String
)