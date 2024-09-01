package com.hakancevik.youtubemusicclone.ui.home.trackdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakancevik.domain.entity.ResultData
import com.hakancevik.domain.entity.trackdata.TrackData
import com.hakancevik.domain.usecase.GetTrackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TrackDetailViewModel @Inject constructor(
    private val getTrackUseCase: GetTrackUseCase
) : ViewModel() {

    private val _trackState = MutableStateFlow<ResultData<TrackData>>(ResultData.Loading())
    val trackState: StateFlow<ResultData<TrackData>> = _trackState.asStateFlow()



    fun getTrack(trackId: String) {
        getTrackUseCase(trackId)
            .onEach { result ->
                _trackState.value = result
            }
            .launchIn(viewModelScope)
    }
}