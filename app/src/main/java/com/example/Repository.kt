package com.example

import com.example.hilt.data.Note
import com.example.hilt.data.NoteDao
import com.example.hilt.data.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(private val noteDao: NoteDao) {

    fun getAllNote(): Flow<List<Note>> = noteDao.getAllNote().flowOn(Dispatchers.IO).conflate()

    suspend fun addNote(note:Note){
        noteDao.addNote(note)
    }

    suspend fun removeNote(note:Note){
        noteDao.removeNote(note)
    }
}