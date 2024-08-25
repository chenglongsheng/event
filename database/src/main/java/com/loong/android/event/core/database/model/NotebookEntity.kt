package com.loong.android.event.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

/**
 * 记事本表
 */
@Entity("notebook")
data class NotebookEntity(
    @PrimaryKey
    @ColumnInfo("notebook_id")
    val notebookId: String,

    @ColumnInfo("notebook_name")
    val notebookName: String = "",

    val description: String = "",

    @ColumnInfo("cover_img")
    val coverImg: String = "",

    @ColumnInfo("note_count")
    val noteCount: Int = 0,

    @ColumnInfo("create_time")
    val createTime: Instant,

    @ColumnInfo("update_time")
    val updateTime: Instant
)