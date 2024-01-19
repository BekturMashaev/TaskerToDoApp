package com.example.taskertodo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskertodo.presentation.screens.screen_add_category.AddCategoryScreen
import com.example.taskertodo.presentation.screens.screen_add_category.AddCategoryScreenViewModel
import com.example.taskertodo.presentation.screens.screen_add_task.AddTaskScreen
import com.example.taskertodo.presentation.screens.screen_add_task.AddTaskViewModel
import com.example.taskertodo.presentation.screens.screen_main.MainScreen
import com.example.taskertodo.presentation.screens.screen_main.MainScreenViewModel

@Composable
fun TaskerNavigation() {
    val navController = rememberNavController()
    val mainScreenRoute = Screens.MainScreen.route.toString()
    val addTaskScreenRoute = Screens.AddTaskScreen.route.toString()
    val addCategoryScreenRoute = Screens.AddCategoryScreen.route.toString()
    val addTaskViewModel: AddTaskViewModel = viewModel()
    val addCategoryScreenViewModel: AddCategoryScreenViewModel = viewModel()
    val mainScreenViewModel: MainScreenViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = mainScreenRoute,
    ) {
        composable(mainScreenRoute) {
            MainScreen(
                navController = navController, uiState = mainScreenViewModel.uiState
            )
        }
        composable(addTaskScreenRoute) {
            AddTaskScreen(
                navController = navController,
                onSaveClick = addTaskViewModel::addNewTask,
                uiState = addTaskViewModel.uiState,
                onUpdateLocalDate = addTaskViewModel::updateDate,
                onUpdateTaskTitle = addTaskViewModel::updateTitleText,
                onUpdateLocalTime = addTaskViewModel::updateTime,
                selectCategory = addTaskViewModel::updateTaskCategory,
                onUpdateTaskColor = addTaskViewModel::updateTaskCategoryColor
            )
        }
        composable(addCategoryScreenRoute) {
            AddCategoryScreen(
                navController = navController,
                updateCategoryColor = addCategoryScreenViewModel::updateCategoryColor,
                updateCategoryTitle = addCategoryScreenViewModel::updateCategoryTitle,
                saveNewCategory = addCategoryScreenViewModel::addNewCategory,
            )
        }
    }
}