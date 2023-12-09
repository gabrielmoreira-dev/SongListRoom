package br.edu.ifsp.songlistroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.songlistroom.data.Song
import br.edu.ifsp.songlistroom.data.SongDatabase
import br.edu.ifsp.songlistroom.repository.SongRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SongViewModel(application: Application): AndroidViewModel(application) {
    private val repository: SongRepository

    init {
        repository = SongRepository(SongDatabase.getDatabase(application).songDAO())
    }

    fun insert(song: Song) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(song)
    }
}