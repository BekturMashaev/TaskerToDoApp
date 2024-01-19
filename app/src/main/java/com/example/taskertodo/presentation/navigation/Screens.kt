package com.example.taskertodo.presentation.navigation

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import com.example.taskertodo.R

@SuppressLint("SupportAnnotationUsage")
sealed class Screens(@StringRes val route:Int) {
    data object MainScreen:Screens(R.string.main_screen_route)
    data object AddTaskScreen:Screens(R.string.add_task_screen_route)
    object AddCategoryScreen:Screens(R.string.add_category_screen_route)
}