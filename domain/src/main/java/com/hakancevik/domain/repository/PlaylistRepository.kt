package com.hakancevik.domain.repository

import com.hakancevik.domain.entity.ResultData
import com.hakancevik.domain.entity.playlistdata.PlaylistData
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {
    fun getPlaylist(id: String): Flow<ResultData<PlaylistData>>
}