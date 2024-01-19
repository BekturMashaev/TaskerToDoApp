package com.example.taskertodo.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.taskertodo.R
import com.example.taskertodo.presentation.navigation.Screens
import com.example.taskertodo.presentation.theme.TaskerBlue

@Preview
@Composable
fun TaskerScaffoldPreview() {
    MaterialTheme {
        TaskerScaffold(
            title = "Today",
            actionIcon = painterResource(id = R.drawable.more_icon),
            isFloatingVisible = true,
            navController = rememberNavController(),
            contentCallBack = { innerPadding -> }
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ResourceType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskerScaffold(
    modifier: Modifier = Modifier,
    title: String,
    actionIcon: Painter,
    contentCallBack: @Composable (PaddingValues) -> Unit,
    isFloatingVisible: Boolean,
    navController: NavController
) {
    Scaffold(modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(title = {
                Text(
                    modifier = modifier.padding(start = 60.dp, top = 16.dp),
                    text = title,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    letterSpacing = 0.32.sp,
                )
            }, actions = {
                IconButton(modifier = modifier.padding(top = 16.dp),
                    onClick = { /*TODO*/ },
                    content = {
                        androidx.compose.material3.Icon(
                            painter = actionIcon, contentDescription = null, tint = TaskerBlue
                        )
                    })
            })
        },
        content = { innerPadding ->
            contentCallBack.invoke(innerPadding)
        },
        floatingActionButton = {
            if (isFloatingVisible) {
                FABComponent(
                ) {
                    navController.navigate(
                        if (it) Screens.AddTaskScreen.route.toString()
                        else Screens.AddCategoryScreen.route.toString()
                    )
                }
            }
        })
}