package br.edu.ifsp.songlistroom.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface SongDAO {
    @Insert
    fun insert(song: Song)
}