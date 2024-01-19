@file:OptIn(ExperimentalMaterial3Api::class)
@file:Suppress("UNUSED_EXPRESSION")

package com.example.taskertodo.presentation.screens.screen_add_task

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskertodo.R
import com.example.taskertodo.data.models.CategoryModel
import com.example.taskertodo.presentation.components.CategoryList
import com.example.taskertodo.presentation.components.CircleColorComponent
import com.example.taskertodo.presentation.components.CustomDatePicker
import com.example.taskertodo.presentation.components.CustomTimePicker
import com.example.taskertodo.presentation.components.parse
import com.example.taskertodo.presentation.screens.screen_add_task.model.AddTaskBottomMenuActions
import com.example.taskertodo.presentation.theme.EMPTY_STRING
import com.example.taskertodo.presentation.theme.TaskerBlue
import com.example.taskertodo.presentation.theme.TaskerGray
import com.example.taskertodo.presentation.theme.dp12
import com.example.taskertodo.presentation.theme.dp25
import com.example.taskertodo.presentation.theme.dp3
import com.example.taskertodo.presentation.theme.dp4
import com.example.taskertodo.presentation.theme.dp5
import com.example.taskertodo.presentation.theme.dp6
import com.example.taskertodo.presentation.theme.dp60
import com.example.taskertodo.presentation.theme.sp15
import com.example.taskertodo.presentation.theme.sp18


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
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
    onUpdateTaskColor: (String) -> Unit
) {
    var bottomMenuClickState by remember {
        mutableStateOf(AddTaskBottomMenuActions.DEFAULT)
    }
    val context = LocalContext.current
    val source = remember {
        MutableInteractionSource()
    }
    val controller = LocalSoftwareKeyboardController.current
    val isPressed: Boolean by source.collectIsPressedAsState()

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
                                context.getString(R.string.choose_a_category),
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
                            onSaveClick()
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
        content = { innerPadding ->
            TaskTextField(
                innerPadding = innerPadding,
                onUpdateStringTitle = {
                    onUpdateTaskTitle(it)
                },
                uiState = uiState,
                source = source
            )
        }, bottomBar = {
            if (isPressed) {
                bottomMenuClickState = AddTaskBottomMenuActions.DEFAULT
            }
            val height = if (bottomMenuClickState != AddTaskBottomMenuActions.DEFAULT) {
                modifier.fillMaxHeight(0.6f)
            } else {
                modifier.wrapContentHeight()
            }
            Column(
                modifier = height
            ) {
                TaskBottomMenu(
                    onClick = {
                        bottomMenuClickState = it
                    },
                    onChangeControllerStateClick = {
                        if (it) controller?.hide()
                    },
                    uiState = uiState,
                )
                AnimatedVisibility(visible = bottomMenuClickState != AddTaskBottomMenuActions.DEFAULT) {
                    AnimatedVisibility(visible = bottomMenuClickState == AddTaskBottomMenuActions.DATEPICKER) {
                        CustomDatePicker(
                            onSelectTaskDate = {
                                onUpdateLocalDate(
                                    "${it.year}.${it.monthValue}.${it.dayOfMonth}",
                                )
                            },
                        )
                    }
                    AnimatedVisibility(visible = bottomMenuClickState == AddTaskBottomMenuActions.TIMEPICKER) {
                        CustomTimePicker(
                            onSelectTaskTime = {
                                onUpdateLocalTime(
                                    "${it.hour}:${it.minute}"
                                )
                            }
                        )
                    }
                    AnimatedVisibility(
                        visible = bottomMenuClickState == AddTaskBottomMenuActions.CHOOSECATEGORY,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        uiState.taskCategoryList?.let { it ->
                            CategoryList(
                                categoryList = it,
                                selectCategory = {
                                    selectCategory(it)
                                    onUpdateTaskColor(it.color)
                                },
                                isListForMainScreen = false
                            )
                        }
                    }
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
    source: MutableInteractionSource,
) {
    val focusManager = LocalFocusManager.current
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
                    interactionSource = source,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    trailingIcon = {
                        if (!uiState.taskColor.isNullOrBlank()) Card(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.parse(
                                    uiState.taskColor!!
                                )
                            ),
                        ) {
                        }
                    }
                )
                Row(
                    modifier = modifier.fillMaxWidth()
                ) {
                    if (!uiState.taskDate.isNullOrBlank()) {
                        IconButton(
                            modifier = modifier
                                .size(dp25),
                            onClick = {}
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.calendar),
                                contentDescription = null,
                                tint = Color.Gray
                            )
                        }
                        Text(
                            modifier = modifier.padding(dp4),
                            text = uiState.taskDate ?: EMPTY_STRING,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                        )
                    }
                    if (!uiState.taskTime.isNullOrBlank()) {
                        IconButton(
                            modifier = modifier
                                .size(dp25),
                            onClick = {}
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.alarm),
                                contentDescription = null,
                                tint = Color.Gray
                            )
                        }
                        Text(
                            modifier = modifier.padding(dp4),
                            text = uiState.taskTime ?: EMPTY_STRING,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
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
    onChangeControllerStateClick: (Boolean) -> Unit,
    uiState: AddTaskUiState,
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
                    onChangeControllerStateClick(true)
                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = null,
                    tint = if (uiState.taskDate.isNullOrEmpty()) {
                        if (isSystemInDarkTheme()) Color.White
                        else TaskerGray
                    } else {
                        TaskerBlue
                    }
                )
            }
            IconButton(onClick = {
                onClick(AddTaskBottomMenuActions.TIMEPICKER)
                onChangeControllerStateClick(true)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.alarm),
                    contentDescription = null,
                    tint = if (uiState.taskTime.isNullOrEmpty()) {
                        if (isSystemInDarkTheme()) Color.White
                        else TaskerGray
                    } else {
                        TaskerBlue
                    }
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick = {
                onClick(AddTaskBottomMenuActions.CHOOSECATEGORY)
                onChangeControllerStateClick(true)
            }) {
                Text(
                    text = if (uiState.taskCategory == null) stringResource(R.string.inbox_txt)
                    else uiState.taskCategory?.title!!,
                    fontSize = sp15,
                    color = if (isSystemInDarkTheme()) Color.White
                    else TaskerGray,
                    fontWeight = FontWeight.Medium,
                )
                CircleColorComponent(
                    modifier = modifier
                        .size(dp12)
                        .padding(start = dp3, top = dp3),
                    color = if (!uiState.taskColor.isNullOrBlank()) Color.parse(
                        uiState.taskColor!!
                    )
                    else TaskerBlue
                )
            }
        }
    }
}
