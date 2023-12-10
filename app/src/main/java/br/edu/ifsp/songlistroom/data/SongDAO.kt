package br.edu.ifsp.songlistroom.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SongDAO {
    @Insert
    fun insert(song: Song)

    @Update
    suspend fun update(song: Song)

    @Delete
    suspend fun delete(song: Song)

    @Query("SELECT * FROM song ORDER BY name")
    fun getAllSongs(): LiveData<List<Song>>

    @Query("SELECT * FROM song WHERE id=:id")
    fun getSongById(id: Int): LiveData<Song>
}