package com.hakancevik.data.di

import com.hakancevik.data.api.PlaylistApiService
import com.hakancevik.data.api.TrackApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

    @Provides
    @Singleton
    fun providePlaylistApiService(retrofit: Retrofit): PlaylistApiService {
        return retrofit.create(PlaylistApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideTrackApiService(retrofit: Retrofit): TrackApiService {
        return retrofit.create(TrackApiService::class.java)
    }

}