package com.loong.android.event.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.loong.android.event.database.model.NotebookEntity

@Dao
interface NotebookDao {

    @Insert
    suspend fun insert(notebookEntity: NotebookEntity)

    @Delete
    suspend fun delete(notebookEntity: NotebookEntity)

    @Update
    suspend fun update(notebookEntity: NotebookEntity)

    @Query("SELECT * FROM notebook")
    suspend fun getAllNoteBook(): List<NotebookEntity>

    @Query("SELECT * FROM notebook")
    suspend fun getNotebook(): NotebookEntity?

}