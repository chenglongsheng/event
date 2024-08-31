package com.loong.android.event.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.loong.android.event.database.dao.NoteDao
import com.loong.android.event.database.dao.NotebookDao
import com.loong.android.event.database.dao.TagDao
import com.loong.android.event.database.model.NoteEntity
import com.loong.android.event.database.model.NoteTagCrossRefEntity
import com.loong.android.event.database.model.NotebookEntity
import com.loong.android.event.database.model.TagEntity
import com.loong.android.event.database.util.InstantConverter

@Database(
    entities = [
        NotebookEntity::class,
        NoteEntity::class,
        TagEntity::class,
        NoteTagCrossRefEntity::class
    ],
    version = 1
)
@TypeConverters(
    InstantConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notebookDao(): NotebookDao
    abstract fun noteDao(): NoteDao
    abstract fun tagDao(): TagDao

    companion object {
        @Volatile
        private var db: AppDatabase? = null

        fun get(context: Context): AppDatabase {
            return db ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app-database"
                )
                    .build()
                    .also { db = it }
            }
        }
    }
}