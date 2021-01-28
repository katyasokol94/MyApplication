package com.sokolkatya.myapplication.data.sourse.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sokolkatya.myapplication.data.entities.database.Actor
import com.sokolkatya.myapplication.data.entities.database.Genre
import com.sokolkatya.myapplication.data.entities.database.Movie
import com.sokolkatya.myapplication.data.entities.database.MovieActor
import com.sokolkatya.myapplication.data.entities.database.MovieGenre

@Database(
    entities = [Movie::class, Genre::class, Actor::class, MovieActor::class, MovieGenre::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    abstract fun appDAO(): AppDAO
}