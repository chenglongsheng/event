package com.loong.android.event.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * 笔记和标签关联表
 */
@Entity(tableName = "note_tag_cross_ref", primaryKeys = ["note_id", "tag_id"])
data class NoteTagCrossRefEntity(
    @ColumnInfo("note_id")
    val noteId: String,

    @ColumnInfo("tag_id")
    val tagId: String
)