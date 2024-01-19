package com.example.taskertodo.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


private const val CATEGORY_TABLE_CACHE="category_table_cache"
private const val CATEGORY_CATEGORY_TITLE="category_title"

@Entity(tableName = CATEGORY_TABLE_CACHE)
data class CategoryModelCache(
    @PrimaryKey val id:String,
    @ColumnInfo(name = CATEGORY_CATEGORY_TITLE) val categoryTitle:String,
    val color:String
)
