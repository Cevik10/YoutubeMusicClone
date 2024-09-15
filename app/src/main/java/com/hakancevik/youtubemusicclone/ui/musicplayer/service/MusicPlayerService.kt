package com.hakancevik.youtubemusicclone.ui.musicplayer.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaMetadata
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.hakancevik.youtubemusicclone.R
import com.hakancevik.youtubemusicclone.ui.musicplayer.playermanager.MediaPlayerManager


class MusicPlayerService : Service() {
    private lateinit var notificationManager: NotificationManager
    lateinit var mediaPlayerManager: MediaPlayerManager
    private lateinit var notification: Notification
    private var mediaSession: MediaSessionCompat? = null

    private val notificationDismissedReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == ACTION_NOTIFICATION_DISMISSED) {
                mediaPlayerManager.stopPlayback()
                stopSelf()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        mediaPlayerManager = MediaPlayerManager()

        registerReceiver(
            notificationDismissedReceiver,
            IntentFilter(ACTION_NOTIFICATION_DISMISSED),
            RECEIVER_NOT_EXPORTED
        )
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val songUrl = intent?.getStringExtra("TRACK_URL") // Extract the song URL from the Intent

        when (intent?.action) {
            ACTION_STOP, ACTION_NOTIFICATION_DISMISSED -> {
                stopMediaPlayback()
                return START_NOT_STICKY
            }

            ACTION_PLAY -> {
                if (songUrl != null) {
                    startMediaPlayback(songUrl) // Pass the song URL to start playback
                }
            }

            ACTION_PAUSE -> {
                pauseMediaPlayback()
            }
        }
        return START_STICKY
    }

    private fun stopMediaPlayback() {
        mediaPlayerManager.stopPlayback()
        stopSelf()
        mediaSession?.release()
    }



    private fun startMediaPlayback(songUrl: String) {
        setupMediaSession(songUrl)
        mediaPlayerManager.startPlayback()
        mediaPlayerManager.updatePlaybackState()
        openMusicPlayerNotification()
    }

    private fun pauseMediaPlayback() {
        mediaPlayerManager.pausePlayback()
    }

    private fun setupMediaSession(songUrl: String) {
        if (mediaSession == null) {
            mediaSession = MediaSessionCompat(this, "MusicService")
            mediaSession?.setCallback(object : MediaSessionCompat.Callback() {
                override fun onPlay() {
                    mediaPlayerManager.startPlayback()
                    super.onPlay()
                }

                override fun onPause() {
                    mediaPlayerManager.pausePlayback()
                    super.onPause()
                }

                override fun onSeekTo(pos: Long) {
                    mediaPlayerManager.seekTo(pos)
                    super.onSeekTo(pos)
                }
            })
            mediaSession?.isActive = true
        }

        mediaPlayerManager.initializeMediaPlayer(mediaSession, songUrl) // Pass URL here
        setupMediaMetadata()
    }

    private fun setupMediaMetadata() {
        val mediaMetadata = MediaMetadata.Builder().putLong(
            MediaMetadata.METADATA_KEY_DURATION, mediaPlayerManager.duration?.toLong() ?: 1L
        ).build()
        mediaSession?.setMetadata(MediaMetadataCompat.fromMediaMetadata(mediaMetadata))
    }

    private fun openMusicPlayerNotification() {
        val deleteIntent = Intent(this, MusicPlayerService::class.java).apply {
            action = ACTION_NOTIFICATION_DISMISSED
        }
        val deletePendingIntent = PendingIntent.getService(
            this,
            REQUEST_CODE_DELETE,
            deleteIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        notification =
            NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("Hoochie Coochie Man")
                .setContentText("Muddy Waters").setSmallIcon(R.drawable.ic_launcher_foreground)
                .setSilent(true).setOngoing(true).setDeleteIntent(deletePendingIntent).setStyle(
                    androidx.media.app.NotificationCompat.MediaStyle()
                        .setMediaSession(mediaSession?.sessionToken)
                ).build()

        startForeground(NOTIFICATION_ID, notification)
    }

    fun seekTo(position: Int) {
        mediaPlayerManager.seekTo(position.toLong())
    }

    override fun onDestroy() {
        mediaPlayerManager.releasePlayback()
        unregisterReceiver(notificationDismissedReceiver)
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder {
        return MusicServiceBinder(this)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID, "Music Service Channel", NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    inner class MusicServiceBinder(val service: MusicPlayerService) : Binder()

    companion object {
        const val ACTION_STOP = "com.hakancevik.youtubemusicclone.ACTION_STOP"
        const val ACTION_NOTIFICATION_DISMISSED =
            "com.hakancevik.youtubemusicclone.ACTION_NOTIFICATION_DISMISSED"
        const val ACTION_PLAY = "com.example.ACTION_PLAY"
        const val ACTION_PAUSE = "com.example.ACTION_PAUSE"
        const val CHANNEL_ID = "MusicServiceChannel"
        const val REQUEST_CODE_DELETE = 0
        const val NOTIFICATION_ID = 1
    }
}


//package com.hakancevik.youtubemusicclone.ui.musicplayer.service
//
//import android.app.Notification
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.PendingIntent
//import android.app.Service
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import android.content.IntentFilter
//import android.media.MediaMetadata
//import android.os.Binder
//import android.os.Build
//import android.os.IBinder
//import android.support.v4.media.MediaMetadataCompat
//import android.support.v4.media.session.MediaSessionCompat
//import androidx.annotation.RequiresApi
//import androidx.core.app.NotificationCompat
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelStore
//import androidx.lifecycle.ViewModelStoreOwner
//import com.hakancevik.domain.entity.ResultData
//import com.hakancevik.domain.repository.TrackRepository
//import com.hakancevik.youtubemusicclone.R
//import com.hakancevik.youtubemusicclone.ui.home.trackdetail.TrackDetailViewModel
//import com.hakancevik.youtubemusicclone.ui.musicplayer.playermanager.MediaPlayerManager
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.Job
//import kotlinx.coroutines.flow.catch
//import kotlinx.coroutines.flow.onEach
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//
//@AndroidEntryPoint
//class MusicPlayerService : Service() {
//    private lateinit var notificationManager: NotificationManager
//    lateinit var mediaPlayerManager: MediaPlayerManager
//    private lateinit var notification: Notification
//    private var mediaSession: MediaSessionCompat? = null
//
//
//    @Inject
//    lateinit var trackRepository: TrackRepository
//
//    private val serviceScope = CoroutineScope(Dispatchers.IO + Job())
//
//    private val notificationDismissedReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            if (intent?.action == ACTION_NOTIFICATION_DISMISSED) {
//                mediaPlayerManager.stopPlayback()
//                stopSelf()
//            }
//        }
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onCreate() {
//        super.onCreate()
//
//        createNotificationChannel()
//        mediaPlayerManager = MediaPlayerManager()
//
//        registerReceiver(
//            notificationDismissedReceiver,
//            IntentFilter(ACTION_NOTIFICATION_DISMISSED),
//            RECEIVER_NOT_EXPORTED
//        )
//        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//    }
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        when (intent?.action) {
//            ACTION_STOP, ACTION_NOTIFICATION_DISMISSED -> {
//                stopMediaPlayback()
//                return START_NOT_STICKY
//            }
//
//            ACTION_PLAY -> {
//                val trackId = intent.getStringExtra(EXTRA_TRACK_ID)
//                if (trackId != null) {
//                    playTrack(trackId)
//                }
//            }
//
//            ACTION_PAUSE -> {
//                pauseMediaPlayback()
//            }
//        }
//        return START_STICKY
//    }
//
//
//    private fun startTracking(trackId: String) {
//        serviceScope.launch {
//            trackRepository.getTrack(trackId)
//                .onEach { result ->
//                    // Handle the track data
//                    when (result) {
//                        is ResultData.Success -> {
//                            result.data?.let { trackData ->
//                                trackData.previewUrl?.let { playTrack(it) }
//                            }
//                        }
//
//                        is ResultData.Error -> {
//                            val error = result.exception
//                            // Handle the error
//                        }
//
//                        is ResultData.Loading -> {
//                            // Handle loading state
//                        }
//                    }
//                }
//                .catch { e ->
//                    // Handle exceptions
//                }
//        }
//    }
//
//    private fun stopMediaPlayback() {
//        mediaPlayerManager.stopPlayback()
//        stopSelf()
//        mediaSession?.release()
//    }
//
//    private fun playTrack(previewUrl: String) {
//        setupMediaSession()
//        mediaPlayerManager.startPlayback(previewUrl)
//        mediaPlayerManager.updatePlaybackState()
//        openMusicPlayerNotification()
//    }
//
//    private fun pauseMediaPlayback() {
//        mediaPlayerManager.pausePlayback()
//    }
//
//    private fun setupMediaSession() {
//        if (mediaSession == null) {
//            mediaSession = MediaSessionCompat(this, "MusicService")
//            mediaSession?.setCallback(object : MediaSessionCompat.Callback() {
//                override fun onPlay() {
//                    mediaPlayerManager.startPlayback()
//                    super.onPlay()
//                }
//
//                override fun onPause() {
//                    mediaPlayerManager.pausePlayback()
//                    super.onPause()
//                }
//
//                override fun onSeekTo(pos: Long) {
//                    mediaPlayerManager.seekTo(pos)
//                    super.onSeekTo(pos)
//                }
//            })
//            mediaSession?.isActive = true
//            mediaPlayerManager.initializeMediaPlayer(mediaSession)
//            setupMediaMetadata()
//        }
//    }
//
//    private fun setupMediaMetadata() {
//        val mediaMetadata = MediaMetadata.Builder().putLong(
//            MediaMetadata.METADATA_KEY_DURATION, mediaPlayerManager.duration?.toLong() ?: 1L
//        ).build()
//        mediaSession?.setMetadata(MediaMetadataCompat.fromMediaMetadata(mediaMetadata))
//    }
//
//    private fun openMusicPlayerNotification() {
//        val deleteIntent = Intent(this, MusicPlayerService::class.java).apply {
//            action = ACTION_NOTIFICATION_DISMISSED
//        }
//        val deletePendingIntent = PendingIntent.getService(
//            this,
//            REQUEST_CODE_DELETE,
//            deleteIntent,
//            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
//        )
//
//        notification =
//            NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("Playing Track")
//                .setContentText("Track details here").setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setSilent(true).setOngoing(true).setDeleteIntent(deletePendingIntent).setStyle(
//                    androidx.media.app.NotificationCompat.MediaStyle()
//                        .setMediaSession(mediaSession?.sessionToken)
//                ).build()
//
//        startForeground(NOTIFICATION_ID, notification)
//    }
//
//    fun seekTo(position: Int) {
//        mediaPlayerManager.seekTo(position.toLong())
//    }
//
//    override fun onDestroy() {
//        mediaPlayerManager.releasePlayback()
//        unregisterReceiver(notificationDismissedReceiver)
//        super.onDestroy()
//    }
//
//    override fun onBind(intent: Intent): IBinder {
//        return MusicServiceBinder(this)
//    }
//
//    private fun createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val serviceChannel = NotificationChannel(
//                CHANNEL_ID, "Music Service Channel", NotificationManager.IMPORTANCE_DEFAULT
//            )
//            val manager = getSystemService(NotificationManager::class.java)
//            manager.createNotificationChannel(serviceChannel)
//        }
//    }
//
//    inner class MusicServiceBinder(val service: MusicPlayerService) : Binder()
//
//    companion object {
//        const val ACTION_STOP = "com.hakancevik.youtubemusicclone.ACTION_STOP"
//        const val ACTION_NOTIFICATION_DISMISSED =
//            "com.hakancevik.youtubemusicclone.ACTION_NOTIFICATION_DISMISSED"
//        const val ACTION_PLAY = "com.example.ACTION_PLAY"
//        const val ACTION_PAUSE = "com.example.ACTION_PAUSE"
//        const val EXTRA_TRACK_ID = "com.example.EXTRA_TRACK_ID"
//        const val CHANNEL_ID = "MusicServiceChannel"
//        const val REQUEST_CODE_DELETE = 0
//        const val NOTIFICATION_ID = 1
//    }
//}
