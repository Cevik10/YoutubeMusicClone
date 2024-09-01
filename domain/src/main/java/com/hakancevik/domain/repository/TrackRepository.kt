package com.hakancevik.domain.repository

import com.hakancevik.domain.entity.ResultData
import com.hakancevik.domain.entity.trackdata.TrackData
import kotlinx.coroutines.flow.Flow

interface TrackRepository {
    fun getTrack(trackId: String): Flow<ResultData<TrackData>>
}