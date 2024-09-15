package com.hakancevik.data.api

import com.hakancevik.data.model.track.TrackDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface TrackApiService {
    @GET("track/{id}")
    suspend fun getTrack(@Path("id") trackId: String): TrackDTO
}