package br.edu.ifsp.songlistroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.songlistroom.data.Song
import br.edu.ifsp.songlistroom.data.SongDatabase
import br.edu.ifsp.songlistroom.repository.SongRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SongViewModel(application: Application): AndroidViewModel(application) {
    private val repository: SongRepository
    var songList: LiveData<List<Song>>
    lateinit var song: LiveData<Song>

    init {
        repository = SongRepository(SongDatabase.getDatabase(application).songDAO())
        songList = repository.getAllSongs()
    }

    fun insert(song: Song) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(song)
    }

    fun update(song: Song) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(song)
    }

    fun delete(song: Song) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(song)
    }

    fun getContactById(id: Int) = viewModelScope.launch {
        song = repository.getSongById(id)
    }
}