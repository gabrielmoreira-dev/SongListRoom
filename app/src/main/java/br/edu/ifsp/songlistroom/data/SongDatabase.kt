package br.edu.ifsp.songlistroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Song::class], version = 1)
abstract class SongDatabase: RoomDatabase() {
    abstract fun songDAO(): SongDAO

    companion object {
        private var instance: SongDatabase? = null

        fun getDatabase(context: Context) = instance ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                SongDatabase::class.java,
                "songlistroom.db"
            ).build().let {
                instance = it
                it
            }
        }
    }
}