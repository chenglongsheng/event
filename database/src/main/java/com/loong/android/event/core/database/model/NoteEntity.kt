package com.loong.android.event.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

/**
 * 笔记表
 */
@Entity("note")
data class NoteEntity(
    @PrimaryKey
    @ColumnInfo("note_id")
    val noteId: String,

    @ColumnInfo("notebook_id")
    val notebookId: String = "",

    @ColumnInfo("note_name")
    val noteName: String = "",

    @ColumnInfo("pre_content")
    val preContent: String = "",

    @ColumnInfo("content_path")
    val contentPath: String = "",

    @ColumnInfo("bind_time")
    val bindTime: Instant,

    @ColumnInfo("edit_time")
    val editTime: Instant,

    @ColumnInfo("create_time")
    val createTime: Instant,

    @ColumnInfo("update_time")
    val updateTime: Instant,
)
