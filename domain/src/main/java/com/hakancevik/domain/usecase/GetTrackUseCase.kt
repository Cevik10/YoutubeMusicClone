package com.hakancevik.domain.usecase

import com.hakancevik.domain.entity.ResultData
import com.hakancevik.domain.entity.trackdata.TrackData
import com.hakancevik.domain.repository.TrackRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrackUseCase @Inject constructor(
    private val trackRepository: TrackRepository
) {
    operator fun invoke(trackId: String): Flow<ResultData<TrackData>> {
        return trackRepository.getTrack(trackId)
    }
}