package com.hakancevik.data.di

import com.hakancevik.data.datasource.musicplayer.PlaylistRepositoryImpl
import com.hakancevik.data.datasource.musicplayer.TrackRepositoryImpl
import com.hakancevik.domain.repository.PlaylistRepository
import com.hakancevik.domain.repository.TrackRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryProvider {

    @Binds
    abstract fun bindPlaylistRepository(
        playlistRepositoryImpl: PlaylistRepositoryImpl
    ): PlaylistRepository

    @Binds
    abstract fun bindTrackRepository(
        trackRepositoryImpl: TrackRepositoryImpl
    ): TrackRepository

}