package com.example.task3.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.task3.entity.Cat
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {
    @Query("SELECT * FROM cat")
    fun getCatList(): Flow<List<Cat>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCat(cat: Cat)
}