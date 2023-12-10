package br.edu.ifsp.songlistroom.repository

import br.edu.ifsp.songlistroom.data.Song
import br.edu.ifsp.songlistroom.data.SongDAO

class SongRepository(private val songDAO: SongDAO) {
    suspend fun insert(song: Song) = songDAO.insert(song)

    suspend fun update(song: Song) = songDAO.update(song)

    suspend fun delete(song: Song) = songDAO.delete(song)

    fun getAllSongs() = songDAO.getAllSongs()

    fun getSongById(id: Int) = songDAO.getSongById(id)
}