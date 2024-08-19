package com.hakancevik.data.model.playlist

import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("checksum") val checksum: String,
    @SerializedName("data") val data: List<Data>
)