package com.hakancevik.data.model.mapper


import com.hakancevik.data.model.track.AlbumDTO
import com.hakancevik.data.model.track.ArtistDTO
import com.hakancevik.data.model.track.ContributorDTO
import com.hakancevik.data.model.track.TrackDTO
import com.hakancevik.domain.entity.trackdata.AlbumData
import com.hakancevik.domain.entity.trackdata.ArtistData
import com.hakancevik.domain.entity.trackdata.ContributorData
import com.hakancevik.domain.entity.trackdata.TrackData

object TrackMapper {

    fun mapToDomain(trackDTO: TrackDTO): TrackData {
        return TrackData(
            album = trackDTO.albumData?.let { mapAlbumToDomain(it) },
            artist = trackDTO.artistData?.let { mapArtistToDomain(it) },
            availableCountries = trackDTO.availableCountries ?: emptyList(),
            beatsPerMinute = trackDTO.beatsPerMinute,
            contributors = trackDTO.contributorData?.map { mapContributorToDomain(it) } ?: emptyList(),
            diskNumber = trackDTO.diskNumber,
            duration = trackDTO.duration,
            explicitContentCover = trackDTO.explicitContentCover,
            explicitContentLyrics = trackDTO.explicitContentLyrics,
            isExplicit = trackDTO.isExplicit,
            gain = trackDTO.gain,
            id = trackDTO.id,
            isrc = trackDTO.isrc,
            link = trackDTO.link,
            imageHash = trackDTO.imageHash,
            previewUrl = trackDTO.preview,
            rank = trackDTO.rank,
            isReadable = trackDTO.isReadable,
            releaseDate = trackDTO.releaseDate,
            shareUrl = trackDTO.share,
            title = trackDTO.title,
            shortTitle = trackDTO.shortTitle,
            position = trackDTO.trackPosition,
            token = trackDTO.trackToken,
            type = trackDTO.type
        )
    }

    private fun mapAlbumToDomain(albumDTO: AlbumDTO?): AlbumData? {
        return albumDTO?.let {
            AlbumData(
                coverUrl = it.cover ?: "",
                coverBigUrl = it.coverBig,
                coverMediumUrl = it.coverMedium,
                coverSmallUrl = it.coverSmall,
                coverXlUrl = it.coverXl,
                id = it.id,
                link = it.link,
                md5Image = it.md5Image,
                releaseDate = it.releaseDate,
                title = it.title,
                tracklistUrl = it.tracklist,
                type = it.type
            )
        }
    }

    private fun mapArtistToDomain(artistDTO: ArtistDTO?): ArtistData? {
        return artistDTO?.let {
            ArtistData(
                id = it.id,
                link = it.link,
                name = it.name,
                pictureUrl = it.picture,
                pictureBigUrl = it.pictureBig,
                pictureMediumUrl = it.pictureMedium,
                pictureSmallUrl = it.pictureSmall,
                pictureXlUrl = it.pictureXl,
                isRadio = it.isRadio,
                shareUrl = it.share,
                tracklistUrl = it.tracklist,
                type = it.type
            )
        }
    }

    private fun mapContributorToDomain(contributorDTO: ContributorDTO?): ContributorData {
        return contributorDTO?.let {
            ContributorData(
                id = it.id,
                link = it.link,
                name = it.name,
                pictureUrl = it.picture,
                pictureBigUrl = it.pictureBig,
                pictureMediumUrl = it.pictureMedium,
                pictureSmallUrl = it.pictureSmall,
                pictureXlUrl = it.pictureXl,
                isRadio = it.isRadio,
                role = it.role,
                shareUrl = it.share,
                tracklistUrl = it.tracklist,
                type = it.type
            )
        } ?: ContributorData(
            id = 0,
            link = "",
            name = "",
            pictureUrl = "",
            pictureBigUrl = "",
            pictureMediumUrl = "",
            pictureSmallUrl = "",
            pictureXlUrl = "",
            isRadio = false,
            role = "",
            shareUrl = "",
            tracklistUrl = "",
            type = ""
        )
    }
}