package com.example.taskertodo.presentation.screens.screen_main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.taskertodo.R
import com.example.taskertodo.presentation.components.CategoryList
import com.example.taskertodo.presentation.components.TaskList
import com.example.taskertodo.presentation.components.TaskerScaffold
import com.example.taskertodo.presentation.theme.TaskerBlue
import com.example.taskertodo.presentation.theme.dp0
import com.example.taskertodo.presentation.theme.dp10
import com.example.taskertodo.presentation.theme.dp20
import com.example.taskertodo.presentation.theme.dp200
import com.example.taskertodo.presentation.theme.dp300
import com.example.taskertodo.presentation.theme.dp40
import com.example.taskertodo.presentation.theme.dp5
import com.example.taskertodo.presentation.theme.sp18

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    uiState: MainScreenUiState
) {
    TaskerScaffold(
        title = stringResource(id = R.string.main_screen_title),
        actionIcon = painterResource(id = R.drawable.more_icon),
        isFloatingVisible = true,
        navController = navController,
        contentCallBack = { innerPadding ->
            Column(
                modifier = modifier.padding(innerPadding)
            ) {
                uiState.tasks?.let {
                    TaskList(
                        taskList = it,
                        modifier = modifier.heightIn(dp0, dp300)
                    )
                }
                Row {
                    Spacer(modifier =modifier.width(dp40))
                    Text(
                        text = stringResource(R.string.lists),
                        fontSize = sp18,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        modifier=modifier.padding(vertical = dp5)
                    )
                }
                uiState.categories?.let {
                    CategoryList(
                        categoryList = it,
                        selectCategory = {},
                        isListForMainScreen = true,
                        modifier = modifier.padding()
                    )
                }
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