package br.edu.ifsp.songlistroom.repository

import br.edu.ifsp.songlistroom.data.Song
import br.edu.ifsp.songlistroom.data.SongDAO

class SongRepository(private val songDAO: SongDAO) {
    suspend fun insert(song: Song) = songDAO.insert(song)

    fun getAllSongs() = songDAO.getAllSongs()
}