package br.edu.ifsp.songlistroom.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SongDAO {
    @Insert
    fun insert(song: Song)

    @Query("SELECT * FROM song ORDER BY name")
    fun getAllSongs(): LiveData<List<Song>>

    @Query("SELECT * FROM song WHERE id=:id")
    fun getSongById(id: Int): LiveData<Song>
}