package com.github.felixgail.musicbot.gplaymusic

import com.github.felixgail.gplaymusic.api.GPlayMusic
import com.github.felixgail.gplaymusic.model.Track
import net.bjoernpetersen.musicbot.api.player.Song
import net.bjoernpetersen.musicbot.api.plugin.IdBase
import net.bjoernpetersen.musicbot.spi.plugin.Provider

@IdBase("Google Play Music")
abstract class GPlayMusicProviderBase : Provider {

    abstract val api: GPlayMusic

    fun getSongFromTrack(track: Track): Song {
        var albumArtUrl: String? = null
        if (track.albumArtRef.isPresent) {
            albumArtUrl = track.albumArtRef.get()[0].url
        }
        return getSongFromInfo(
            track.id, track.title, track.artist,
            Math.toIntExact(track.durationMillis!! / 1000), albumArtUrl
        )
    }

    fun getSongFromInfo(
        id: String, title: String,
        description: String, duration: Int, albumArtUrl: String?
    ): Song {
        return Song(id, this, title, description, duration, albumArtUrl)
    }
}
