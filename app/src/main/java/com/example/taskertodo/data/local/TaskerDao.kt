package com.example.taskertodo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskertodo.data.local.models.CategoryModelCache
import com.example.taskertodo.data.local.models.TaskModelCache
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskerDao {

    /** tasks **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewTask(taskModelCache: TaskModelCache)

    @Query("SELECT * FROM task_table_name")
    fun getAllTasks():List<TaskModelCache>

    /** categories **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewCategory(categoryModelCache: CategoryModelCache)

    @Query("SELECT * FROM category_table_cache")
    fun getAllCategories():Flow<List<CategoryModelCache>>
}