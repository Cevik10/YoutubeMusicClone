package com.hakancevik.data.datasource.musicplayer

import com.hakancevik.data.api.TrackApiService
import com.hakancevik.data.model.mapper.TrackMapper
import com.hakancevik.domain.entity.ResultData
import com.hakancevik.domain.entity.trackdata.TrackData
import com.hakancevik.domain.repository.TrackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class TrackRepositoryImpl @Inject constructor(
    private val trackApiService: TrackApiService
) : TrackRepository {

    override fun getTrack(trackId: String): Flow<ResultData<TrackData>> = flow {
        emit(ResultData.Loading())
        try {
            val track = trackApiService.getTrack(trackId)
            val trackData = TrackMapper.mapToDomain(track)
            emit(ResultData.Success(trackData))
        } catch (e: HttpException) {
            emit(ResultData.Error(e))
        } catch (e: IOException) {
            emit(ResultData.Error(e))
        }
    }
}