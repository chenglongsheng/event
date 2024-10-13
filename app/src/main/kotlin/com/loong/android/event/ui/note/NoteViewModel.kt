package com.loong.android.event.ui.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.loong.android.event.database.AppDatabase
import com.loong.android.event.database.dao.NoteDao
import com.loong.android.event.database.model.NoteEntity
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import java.util.UUID

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteDao: NoteDao by lazy { AppDatabase.get(getApplication()).noteDao() }

    private var note: NoteEntity? = null

    fun createNote() {
        viewModelScope.launch {
            val instant = Clock.System.now()
            val id = UUID.randomUUID().toString()
            val noteEntity = NoteEntity(
                id,
                bindTime = instant,
                editTime = instant,
                createTime = instant,
                updateTime = instant
            )
            note = noteEntity
            noteDao.insert(noteEntity)
        }
    }

    fun openNote(id: String) {
        viewModelScope.launch {
            note = noteDao.getNote(id)
        }
    }

    fun updateNote(content: String) {
        viewModelScope.launch {
            val noteEntity = note?.copy(preContent = content)
            if (noteEntity != null) {
                noteDao.update(noteEntity)
            }
        }
    }

}