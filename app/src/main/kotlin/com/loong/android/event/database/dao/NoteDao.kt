package com.loong.android.event.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.loong.android.event.database.model.NoteEntity

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(noteEntity: NoteEntity)

    @Update
    suspend fun update(noteEntity: NoteEntity)

    @Delete
    suspend fun delete(noteEntity: NoteEntity)

    @Query("SELECT * FROM note WHERE note_id = :noteId")
    suspend fun getNote(noteId: String): NoteEntity?

    @Query("SELECT * FROM note WHERE notebook_id = :notebookId ORDER BY bind_time DESC")
    suspend fun getNoteListOfNotebook(notebookId: String): List<NoteEntity>

    @Query("SELECT * FROM note INNER JOIN note_tag_cross_ref ON note_tag_cross_ref.tag_id = :tagId WHERE note.note_id = note_tag_cross_ref.note_id")
    suspend fun getNoteListOfTag(tagId: String): List<NoteEntity>
}