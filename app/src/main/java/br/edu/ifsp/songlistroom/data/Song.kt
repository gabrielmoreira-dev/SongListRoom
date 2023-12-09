package br.edu.ifsp.songlistroom.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Song(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val artist: String,
    val album: String,
    val image: String,
    val isFavorite: Boolean
)
