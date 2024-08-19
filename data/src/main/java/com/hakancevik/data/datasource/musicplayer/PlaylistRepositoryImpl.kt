package com.hakancevik.data.datasource.musicplayer

import com.hakancevik.data.api.PlaylistApiService
import com.hakancevik.data.model.mapper.toPlaylistData
import com.hakancevik.domain.entity.ResultData
import com.hakancevik.domain.entity.playlistdata.PlaylistData
import com.hakancevik.domain.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class PlaylistRepositoryImpl @Inject constructor(
    private val apiService: PlaylistApiService
) : PlaylistRepository {

    override fun getPlaylist(id: String): Flow<ResultData<PlaylistData>> = flow {
        emit(ResultData.Loading())
        try {
            val playlist = apiService.getPlaylist(id)
            val playlistData = playlist.toPlaylistData()
            emit(ResultData.Success(playlistData))
        } catch (e: Exception) {
            emit(ResultData.Error(e))
        }
    }
}