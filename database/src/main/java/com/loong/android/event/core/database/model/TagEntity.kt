package com.loong.android.event.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

/**
 * 标签表
 */
@Entity("tag")
data class TagEntity(
    @ColumnInfo("tag_id")
    @PrimaryKey
    val tagId: String,

    @ColumnInfo("tag_name")
    val tagName: String,

    @ColumnInfo("create_time")
    val createTime: Instant
)