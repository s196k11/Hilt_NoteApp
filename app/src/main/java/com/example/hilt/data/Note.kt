package com.example.hilt.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NoteTbl")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val Id : Int = 0,

    @ColumnInfo(name = "title")
    var title:String,

    @ColumnInfo(name = "description")
    var description:String
)
