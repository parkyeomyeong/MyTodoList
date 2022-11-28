package com.kosa.todolist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ToDoEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getTodoDao() : ToDoDao

    //이 모든건 싱글턴 패튼을 위한 건지 알겠지?
    //근데 이거 object가 뭔지는 정확히 잘 모르겠다.. 변순가?? 아니면 그냥 정의내리는 무엇인가 ..?
    companion object{
        val databaseName = "db_todo"
        var appDatabase : AppDatabase? = null

        fun getInstance(context : Context): AppDatabase?{
            if(appDatabase == null){
                appDatabase = Room.databaseBuilder(context,
                AppDatabase::class.java,
                databaseName).build()
            }
            return appDatabase
        }
    }
}