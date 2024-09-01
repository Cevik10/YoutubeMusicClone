package com.hakancevik.data.model.mapper


import com.hakancevik.data.model.track.Track
import com.hakancevik.domain.entity.trackdata.AlbumData
import com.hakancevik.domain.entity.trackdata.ArtistData
import com.hakancevik.domain.entity.trackdata.ContributorData
import com.hakancevik.domain.entity.trackdata.TrackData

object TrackMapper {

    fun mapToDomain(track: Track): TrackData {
        return TrackData(
            album = mapAlbumToDomain(track.albumData),
            artist = mapArtistToDomain(track.artistData),
            availableCountries = track.availableCountries ?: emptyList(), // Safe null handling
            beatsPerMinute = track.beatsPerMinute,
            contributors = track.contributorData?.map { mapContributorToDomain(it) } ?: emptyList(), // Safe null handling
            diskNumber = track.diskNumber,
            duration = track.duration,
            explicitContentCover = track.explicitContentCover,
            explicitContentLyrics = track.explicitContentLyrics,
            isExplicit = track.isExplicit,
            gain = track.gain,
            id = track.id,
            isrc = track.isrc,
            link = track.link,
            imageHash = track.imageHash,
            previewUrl = track.preview,
            rank = track.rank,
            isReadable = track.isReadable,
            releaseDate = track.releaseDate,
            shareUrl = track.share,
            title = track.title,
            shortTitle = track.shortTitle,
            position = track.trackPosition,
            token = track.trackToken,
            type = track.type
        )
    }

    private fun mapAlbumToDomain(album: AlbumData): AlbumData {
        return AlbumData(
            coverUrl = album.coverUrl ?: "",  // Provide a default value if coverUrl is null
            coverBigUrl = album.coverBigUrl,
            coverMediumUrl = album.coverMediumUrl,
            coverSmallUrl = album.coverSmallUrl,
            coverXlUrl = album.coverXlUrl,
            id = album.id,
            link = album.link,
            md5Image = album.md5Image,
            releaseDate = album.releaseDate,
            title = album.title,
            tracklistUrl = album.tracklistUrl,
            type = album.type
        )
    }

    private fun mapArtistToDomain(artist: ArtistData): ArtistData {
        return ArtistData(
            id = artist.id,
            link = artist.link,
            name = artist.name,
            pictureUrl = artist.pictureUrl ?: "",  // Provide a default value if pictureUrl is null
            pictureBigUrl = artist.pictureBigUrl ?: "",
            pictureMediumUrl = artist.pictureMediumUrl ?: "",
            pictureSmallUrl = artist.pictureSmallUrl ?: "",
            pictureXlUrl = artist.pictureXlUrl ?: "",
            isRadio = artist.isRadio,
            shareUrl = artist.shareUrl ?: "",
            tracklistUrl = artist.tracklistUrl ?: "",
            type = artist.type
        )
    }

    private fun mapContributorToDomain(contributor: ContributorData): ContributorData {
        return ContributorData(
            id = contributor.id,
            link = contributor.link,
            name = contributor.name,
            pictureUrl = contributor.pictureUrl,
            pictureBigUrl = contributor.pictureBigUrl,
            pictureMediumUrl = contributor.pictureMediumUrl,
            pictureSmallUrl = contributor.pictureSmallUrl,
            pictureXlUrl = contributor.pictureXlUrl,
            isRadio = contributor.isRadio,
            role = contributor.role,
            shareUrl = contributor.shareUrl,
            tracklistUrl = contributor.tracklistUrl,
            type = contributor.type
        )
    }
}