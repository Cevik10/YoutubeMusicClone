package com.hakancevik.domain.usecase

import com.hakancevik.domain.entity.ResultData
import com.hakancevik.domain.entity.playlistdata.PlaylistData
import com.hakancevik.domain.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    operator fun invoke(id: String): Flow<ResultData<PlaylistData>> {
        return repository.getPlaylist(id)
    }
}