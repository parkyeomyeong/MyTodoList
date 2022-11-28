package com.kosa.todolist.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoEntity (
    @PrimaryKey(autoGenerate = true) var id: Int? = null,//autoGenerate는 생성하면 자동으로 1씩 증가하는 마치 sequence와 같다
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "importance") val importance: Int
)