//package com.hakancevik.data
//
//import com.google.gson.annotations.SerializedName
//
//data class Album(
//    @SerializedName("cover") val cover: String,
//    @SerializedName("cover_big") val coverBig: String,
//    @SerializedName("cover_medium") val coverMedium: String,
//    @SerializedName("cover_small") val coverSmall: String,
//    @SerializedName("cover_xl") val coverXl: String,
//    @SerializedName("id") val id: String,
//    @SerializedName("md5_image") val md5Image: String,
//    @SerializedName("title") val title: String,
//    @SerializedName("tracklist") val tracklist: String,
//    @SerializedName("type") val type: String
//)
//
//data class Artist(
//    @SerializedName("id") val id: String,
//    @SerializedName("link") val link: String,
//    @SerializedName("name") val name: String,
//    @SerializedName("tracklist") val tracklist: String,
//    @SerializedName("type") val type: String
//)
//
//data class Creator(
//    @SerializedName("id") val id: String,
//    @SerializedName("name") val name: String,
//    @SerializedName("tracklist") val tracklist: String,
//    @SerializedName("type") val type: String
//)
//
//data class Track(
//    @SerializedName("album") val album: Album,
//    @SerializedName("artist") val artist: Artist,
//    @SerializedName("duration") val duration: String,
//    @SerializedName("explicit_content_cover") val explicitContentCover: Int,
//    @SerializedName("explicit_content_lyrics") val explicitContentLyrics: Int,
//    @SerializedName("explicit_lyrics") val explicitLyrics: Boolean,
//    @SerializedName("id") val id: String,
//    @SerializedName("link") val link: String,
//    @SerializedName("md5_image") val md5Image: String,
//    @SerializedName("preview") val preview: String,
//    @SerializedName("rank") val rank: String,
//    @SerializedName("readable") val readable: Boolean,
//    @SerializedName("time_add") val timeAdd: Int,
//    @SerializedName("title") val title: String,
//    @SerializedName("title_short") val titleShort: String,
//    @SerializedName("title_version") val titleVersion: String,
//    @SerializedName("type") val type: String
//)
//
//data class Playlist(
//    @SerializedName("checksum") val checksum: String,
//    @SerializedName("collaborative") val collaborative: Boolean,
//    @SerializedName("creation_date") val creationDate: String,
//    @SerializedName("creator") val creator: Creator,
//    @SerializedName("description") val description: String,
//    @SerializedName("duration") val duration: Int,
//    @SerializedName("fans") val fans: Int,
//    @SerializedName("id") val id: String,
//    @SerializedName("is_loved_track") val isLovedTrack: Boolean,
//    @SerializedName("link") val link: String,
//    @SerializedName("md5_image") val md5Image: String,
//    @SerializedName("nb_tracks") val numberOfTracks: Int,
//    @SerializedName("picture") val picture: String,
//    @SerializedName("picture_big") val pictureBig: String,
//    @SerializedName("picture_medium") val pictureMedium: String,
//    @SerializedName("picture_small") val pictureSmall: String,
//    @SerializedName("picture_type") val pictureType: String,
//    @SerializedName("picture_xl") val pictureXl: String,
//    @SerializedName("public") val isPublic: Boolean,
//    @SerializedName("share") val share: String,
//    @SerializedName("title") val title: String,
//    @SerializedName("tracklist") val tracklist: String,
//    @SerializedName("tracks") val tracks: Tracks,
//    @SerializedName("type") val type: String
//)
//
//data class Tracks(
//    @SerializedName("checksum") val checksum: String,
//    @SerializedName("data") val data: List<Track>
//)
