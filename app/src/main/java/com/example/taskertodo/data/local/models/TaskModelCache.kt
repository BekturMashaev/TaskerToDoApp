package com.example.taskertodo.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.security.PrivateKey

private const val TASK_TABLE_NAME="task_table_name"
private const val TASK_TEXT="task_text"
private const val TASK_DATE="task_date"
private const val TASK_TIME="task_time"
private const val TASK_CATEGORY_ID="task_category_id"
private const val TASK_CATEGORY_COLOR="task_category_color"

@Entity(tableName = TASK_TABLE_NAME)
data class TaskModelCache (
    @PrimaryKey val taskId:String,
    @ColumnInfo(name = TASK_TEXT) val taskText:String,
    @ColumnInfo(name = TASK_DATE) val taskDate:String,
    @ColumnInfo(name = TASK_TIME) val taskTime:String,
    @ColumnInfo(name = TASK_CATEGORY_ID) val categoryId:String,
    @ColumnInfo(name = TASK_CATEGORY_COLOR) val categoryColor:String,
)