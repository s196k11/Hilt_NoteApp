package com.example.hilt.data

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao{
    @Query("select * from NoteTbl")
    fun getAllNote(): Flow<List<Note>>

    @Insert()
    suspend fun addNote(note:Note)

    @Delete()
    suspend fun removeNote(note:Note)
}