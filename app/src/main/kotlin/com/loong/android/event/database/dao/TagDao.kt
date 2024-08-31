package com.loong.android.event.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.loong.android.event.database.model.NoteEntity
import com.loong.android.event.database.model.TagEntity

@Dao
interface TagDao {

    @Insert
    suspend fun insert(tagEntity: TagEntity)

    @Delete
    suspend fun delete(noteEntity: NoteEntity)

    @Update
    suspend fun update(tagEntity: TagEntity)

    @Query("SELECT * FROM tag")
    suspend fun getTagList():List<TagEntity>

    @Query("SELECT * FROM tag INNER JOIN note_tag_cross_ref ON note_tag_cross_ref.note_id = :noteId WHERE tag.tag_id = note_tag_cross_ref.tag_id")
    suspend fun getTagListOfNote(noteId: String): List<TagEntity>

}