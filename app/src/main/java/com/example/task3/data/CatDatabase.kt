package com.example.task3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.task3.entity.Cat

@Database(entities = [Cat::class], version = 1, exportSchema = false)
abstract class CatDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao

    companion object {
        fun getDatabase(context: Context): CatDatabase {
            return Room.databaseBuilder(context, CatDatabase::class.java, "cat_database")
                .build()
        }

    }
}