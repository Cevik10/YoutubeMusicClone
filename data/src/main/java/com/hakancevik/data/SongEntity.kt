//package com.hakancevik.data
//
//data class Song(
//    @SerializedName("album") val album: Album,
//    @SerializedName("artist") val artist: Artist,
//    @SerializedName("available_countries") val availableCountries: List<String>,
//    @SerializedName("bpm") val bpm: Double,
//    @SerializedName("contributors") val contributors: List<Contributor>,
//    @SerializedName("disk_number") val diskNumber: Int,
//    @SerializedName("duration") val duration: Int,
//    @SerializedName("explicit_content_cover") val explicitContentCover: Int,
//    @SerializedName("explicit_content_lyrics") val explicitContentLyrics: Int,
//    @SerializedName("explicit_lyrics") val explicitLyrics: Boolean,
//    @SerializedName("gain") val gain: Double,
//    @SerializedName("id") val id: Int,
//    @SerializedName("isrc") val isrc: String,
//    @SerializedName("link") val link: String,
//    @SerializedName("md5_image") val md5Image: String,
//    @SerializedName("preview") val preview: String,
//    @SerializedName("rank") val rank: Int,
//    @SerializedName("readable") val readable: Boolean,
//    @SerializedName("release_date") val releaseDate: String,
//    @SerializedName("share") val share: String,
//    @SerializedName("title") val title: String,
//    @SerializedName("title_short") val titleShort: String,
//    @SerializedName("title_version") val titleVersion: String,
//    @SerializedName("track_position") val trackPosition: Int,
//    @SerializedName("track_token") val trackToken: String,
//    @SerializedName("type") val type: String
//)
//
//data class Contributor(
//    @SerializedName("id") val id: Int,
//    @SerializedName("link") val link: String,
//    @SerializedName("name") val name: String,
//    @SerializedName("picture") val picture: String,
//    @SerializedName("picture_big") val pictureBig: String,
//    @SerializedName("picture_medium") val pictureMedium: String,
//    @SerializedName("picture_small") val pictureSmall: String,
//    @SerializedName("picture_xl") val pictureXl: String,
//    @SerializedName("radio") val radio: Boolean,
//    @SerializedName("role") val role: String,
//    @SerializedName("share") val share: String,
//    @SerializedName("tracklist") val tracklist: String,
//    @SerializedName("type") val type: String
//)
//
//data class Artist(
//    @SerializedName("id") val id: Int,
//    @SerializedName("link") val link: String,
//    @SerializedName("name") val name: String,
//    @SerializedName("picture") val picture: String,
//    @SerializedName("picture_big") val pictureBig: String,
//    @SerializedName("picture_medium") val pictureMedium: String,
//    @SerializedName("picture_small") val pictureSmall: String,
//    @SerializedName("picture_xl") val pictureXl: String,
//    @SerializedName("radio") val radio: Boolean,
//    @SerializedName("share") val share: String,
//    @SerializedName("tracklist") val tracklist: String,
//    @SerializedName("type") val type: String
//)
//
//data class Album(
//    @SerializedName("cover") val cover: String,
//    @SerializedName("cover_big") val coverBig: String,
//    @SerializedName("cover_medium") val coverMedium: String,
//    @SerializedName("cover_small") val coverSmall: String,
//    @SerializedName("cover_xl") val coverXl: String,
//    @SerializedName("id") val id: Int,
//    @SerializedName("link") val link: String,
//    @SerializedName("md5_image") val md5Image: String,
//    @SerializedName("release_date") val releaseDate: String,
//    @SerializedName("title") val title: String,
//    @SerializedName("tracklist") val tracklist: String,
//    @SerializedName("type") val type: String
//)