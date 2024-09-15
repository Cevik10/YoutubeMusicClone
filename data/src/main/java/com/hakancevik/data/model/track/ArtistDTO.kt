package com.hakancevik.data.model.track

import com.google.gson.annotations.SerializedName

data class ArtistDTO(
    @SerializedName("id")
    val id: String,

    @SerializedName("link")
    val link: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("picture")
    val picture: String,

    @SerializedName("picture_big")
    val pictureBig: String,

    @SerializedName("picture_medium")
    val pictureMedium: String,

    @SerializedName("picture_small")
    val pictureSmall: String,

    @SerializedName("picture_xl")
    val pictureXl: String,

    @SerializedName("radio")
    val isRadio: Boolean,

    @SerializedName("share")
    val share: String,

    @SerializedName("tracklist")
    val tracklist: String,

    @SerializedName("type")
    val type: String
)
