package com.example.taskertodo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskertodo.data.local.models.CategoryModelCache
import com.example.taskertodo.data.local.models.TaskModelCache

@Database(
    entities = [
        TaskModelCache::class,
        CategoryModelCache::class
    ],
    version = 1,
)
abstract class TaskerDatabase : RoomDatabase() {
    abstract fun getTaskDao(): TaskerDao
}