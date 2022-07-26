package com.example.hilt.di

import android.content.Context
import androidx.room.Room
import com.example.hilt.data.NoteDao
import com.example.hilt.data.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context):NoteDatabase = Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        "noteDatabase").fallbackToDestructiveMigration().build()


    @Singleton
    @Provides
    fun provideNotesDao(noteDatabase: NoteDatabase): NoteDao = noteDatabase.noteDao()

}