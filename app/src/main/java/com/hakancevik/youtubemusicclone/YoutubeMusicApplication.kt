package com.hakancevik.youtubemusicclone

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class YoutubeMusicApplication @Inject constructor() : Application()