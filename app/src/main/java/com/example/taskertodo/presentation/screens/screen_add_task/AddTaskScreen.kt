@file:OptIn(ExperimentalMaterial3Api::class)
@file:Suppress("UNUSED_EXPRESSION")

package com.example.taskertodo.presentation.screens.screen_add_task

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskertodo.R
import com.example.taskertodo.R.string.select_category
import com.example.taskertodo.data.models.CategoryModel
import com.example.taskertodo.presentation.components.CategoryList
import com.example.taskertodo.presentation.components.CircleColorComponent
import com.example.taskertodo.presentation.components.CustomDatePicker
import com.example.taskertodo.presentation.components.CustomTimePicker
import com.example.taskertodo.presentation.screens.screen_add_task.model.AddTaskBottomMenuActions
import com.example.taskertodo.presentation.theme.EMPTY_STRING
import com.example.taskertodo.presentation.theme.TaskerBlue
import com.example.taskertodo.presentation.theme.TaskerGray
import com.example.taskertodo.presentation.theme.dp12
import com.example.taskertodo.presentation.theme.dp3
import com.example.taskertodo.presentation.theme.dp5
import com.example.taskertodo.presentation.theme.dp6
import com.example.taskertodo.presentation.theme.dp60
import com.example.taskertodo.presentation.theme.sp15
import com.example.taskertodo.presentation.theme.sp18


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onSaveClick: () -> Unit,
    uiState: AddTaskUiState,
    onUpdateLocalDate: (String) -> Unit,
    onUpdateTaskTitle: (String) -> Unit,
    onUpdateLocalTime: (String) -> Unit,
    selectCategory: (CategoryModel) -> Unit,
) {
    var bottomMenuClickState by remember {
        mutableStateOf(AddTaskBottomMenuActions.TIMEPICKER)
    }
    val context = LocalContext.current
    Scaffold(topBar = {
        TopAppBar(
            title = {
                TextButton(
                    onClick = {
                        navController.popBackStack()
                    },
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel_title_txt),
                        fontSize = sp18,
                        color = TaskerBlue,
                        letterSpacing = (-0.43).sp,
                        fontWeight = FontWeight(400)
                    )
                }
            },
            actions = {
                TextButton(
                    onClick = {
                        if (uiState.taskText.isNullOrEmpty())
                            Toast.makeText(
                                context,
                                context.getString(R.string.fill_in_all_text),
                                Toast.LENGTH_SHORT
                            ).show()
                        else if (uiState.taskCategory == null)
                            Toast.makeText(
                                context,
                                context.getString(select_category),
                                Toast.LENGTH_SHORT
                            ).show()
                        else if (uiState.taskDate.isNullOrEmpty())
                            Toast.makeText(
                                context,
                                context.getString(R.string.select_date),
                                Toast.LENGTH_SHORT
                            ).show()
                        else if (uiState.taskTime.isNullOrEmpty())
                            Toast.makeText(
                                context,
                                context.getString(R.string.select_time),
                                Toast.LENGTH_SHORT
                            ).show()
                        else {
                            onSaveClick
                            navController.popBackStack()
                        }
                    },
                ) {
                    Text(
                        modifier = modifier.padding(end = 16.dp),
                        text = stringResource(R.string.done_title_txt),
                        fontSize = sp18,
                        color = TaskerBlue,
                        fontWeight = FontWeight(600)
                    )
                }
            },
        )
    },
        content = { innerpading ->
            TaskTextField(
                innerPadding = innerpading,
                onUpdateStringTitle = {
                    onUpdateTaskTitle(it)
                },
                uiState = uiState
            )
        }, bottomBar = {
            Column(
                modifier = modifier.fillMaxHeight(0.6f)
            ) {
                TaskBottomMenu(
                    onClick = {
                        bottomMenuClickState = it
                    }
                )
                AnimatedVisibility(
                    visible = bottomMenuClickState == AddTaskBottomMenuActions.CHOOSECATEGORY,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Log.d("AAA", "$bottomMenuClickState")
                    uiState.taskCategoryList?.let { it ->
                        CategoryList(
                            categoryList = it,
                            innerPadding = PaddingValues(),
                            selectCategory = {
                                selectCategory(it)
                            }
                        )
                    }
                }
                AnimatedVisibility(visible = bottomMenuClickState == AddTaskBottomMenuActions.DATEPICKER) {
                    Log.d("AAA", "$bottomMenuClickState")
                    CustomDatePicker(
                        onSelectTaskDate = {
                            onUpdateLocalDate(
                                "${it.dayOfMonth}.${it.month}.${it.year}",
                            )
                        },
                    )
                }
                AnimatedVisibility(visible = bottomMenuClickState == AddTaskBottomMenuActions.TIMEPICKER) {
                    Log.d("AAA", "$bottomMenuClickState")
                    CustomTimePicker(
                        onSelectTaskTime = {
                            onUpdateLocalTime(
                                "${it.hour}:${it.minute}"
                            )
                            Log.d("AAA", "$it")
                        }
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskTextField(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    onUpdateStringTitle: (String) -> Unit,
    uiState: AddTaskUiState,
) {
    var textFieldState by remember {
        mutableStateOf(EMPTY_STRING)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier.fillMaxSize()
        ) {
            IconButton(
                onClick = {},
            ) {
                Icon(
                    modifier = modifier.padding(start = dp6),
                    painter = painterResource(id = R.drawable.unmarked),
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) Color.White
                    else TaskerGray
                )
            }
            Column {
                TextField(
                    value = textFieldState,
                    onValueChange = {
                        textFieldState = it
                        onUpdateStringTitle(it)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.what_do_you_want_to_do_txt),
                            fontSize = sp18,
                            fontWeight = FontWeight(500),
                            color = if (isSystemInDarkTheme()) Color.White
                            else TaskerGray
                        )
                    },
                )
                Row {
                    uiState.taskDate?.let {
                        Text(
                            text = it
                        )
                    }
                    uiState.taskTime?.let {
                        Text(
                            text = it
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TaskBottomMenu(
    onClick: (AddTaskBottomMenuActions) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(bottom = dp5)
    ) {
        Divider()
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(dp60),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    onClick(AddTaskBottomMenuActions.DATEPICKER)
                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) Color.White
                    else TaskerGray
                )
            }
            IconButton(onClick = {
                onClick(AddTaskBottomMenuActions.TIMEPICKER)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.alarm),
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) Color.White
                    else TaskerGray
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick = {
                onClick(AddTaskBottomMenuActions.CHOOSECATEGORY)
            }) {
                Text(
                    text = stringResource(R.string.inbox_txt),
                    fontSize = sp15,
                    color = if (isSystemInDarkTheme()) Color.White
                    else TaskerGray,
                    fontWeight = FontWeight.Medium,
                )
                CircleColorComponent(
                    modifier = modifier
                        .size(dp12)
                        .padding(start = dp3, top = dp3),
                    color = TaskerBlue
                )
            }
        }
    }
}