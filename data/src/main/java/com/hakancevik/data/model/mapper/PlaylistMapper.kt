package com.hakancevik.data.model.mapper

import com.hakancevik.data.model.playlist.Album
import com.hakancevik.data.model.playlist.Artist
import com.hakancevik.data.model.playlist.Creator
import com.hakancevik.data.model.playlist.Data
import com.hakancevik.data.model.playlist.Playlist
import com.hakancevik.data.model.playlist.Tracks
import com.hakancevik.domain.entity.playlistdata.AlbumData
import com.hakancevik.domain.entity.playlistdata.ArtistData
import com.hakancevik.domain.entity.playlistdata.CreatorData
import com.hakancevik.domain.entity.playlistdata.PlaylistData
import com.hakancevik.domain.entity.playlistdata.TrackData
import com.hakancevik.domain.entity.playlistdata.TracksData

fun Playlist.toPlaylistData(): PlaylistData {
    return PlaylistData(
        id = id,
        title = title,
        description = description,
        creator = creator.toCreatorData(),
        duration = duration,
        fans = fans,
        link = link,
        pictureMedium = pictureMedium,
        tracks = tracks.toTracksData()
    )
}

fun Creator.toCreatorData(): CreatorData {
    return CreatorData(
        id = id,
        name = name
    )
}

fun Tracks.toTracksData(): TracksData {
    return TracksData(
        data = data.map { it.toTrackData() }
    )
}

fun Data.toTrackData(): TrackData {
    return TrackData(
        id = id,
        title = title,
        duration = duration,
        artist = artist.toArtistData(),
        album = album.toAlbumData()
    )
}

fun Artist.toArtistData(): ArtistData {
    return ArtistData(
        id = id,
        name = name
    )
}

fun Album.toAlbumData(): AlbumData {
    return AlbumData(
        id = id,
        title = title,
        coverMedium = coverMedium
    )
}