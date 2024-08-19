package com.hakancevik.data.api

import com.hakancevik.data.model.playlist.Playlist
import retrofit2.http.GET
import retrofit2.http.Path


interface PlaylistApiService {
    @GET("playlist/{id}")
    suspend fun getPlaylist(@Path("id") id: String): Playlist
}