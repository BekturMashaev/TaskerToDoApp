package com.example.taskertodo.presentation.screens.screen_main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.taskertodo.R
import com.example.taskertodo.presentation.components.TaskerScaffold
import com.example.taskertodo.presentation.components.CategoryList

@Composable
fun MainScreen(
    modifier: Modifier= Modifier,
    navController: NavController,
    uiState: MainScreenUiState
) {
    TaskerScaffold(
        title = stringResource(id = R.string.main_screen_title),
        actionIcon = painterResource(id = R.drawable.more_icon),
        isFloatingVisible = true,
        navController=navController,
        contentCallBack = {innerPadding->
            uiState.categories?.let {
                CategoryList(
                    categoryList = it,
                    innerPadding = innerPadding,
                    selectCategory = {}
                )
            }
        }
    )
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(
        navController = rememberNavController(),
        uiState = MainScreenUiState()
    )
}