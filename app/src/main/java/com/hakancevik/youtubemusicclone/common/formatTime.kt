package com.hakancevik.youtubemusicclone.common

import androidx.compose.runtime.Composable

@Composable
fun formatTime(ms: Float): String {
    val totalSeconds = ms.toInt() / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%d:%02d", minutes, seconds)
}