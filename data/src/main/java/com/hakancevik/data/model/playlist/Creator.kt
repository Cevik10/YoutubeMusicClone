package com.hakancevik.data.model.playlist

import com.google.gson.annotations.SerializedName

data class Creator(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("tracklist") val tracklist: String,
    @SerializedName("type") val type: String
)