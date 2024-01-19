package com.example.taskertodo.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskertodo.R
import com.example.taskertodo.data.models.TaskModel
import com.example.taskertodo.presentation.theme.dp10
import com.example.taskertodo.presentation.theme.dp20
import com.example.taskertodo.presentation.theme.dp25
import com.example.taskertodo.presentation.theme.dp4
import com.example.taskertodo.presentation.theme.dp5
import com.example.taskertodo.presentation.theme.sp18

@Composable
fun TaskList(
    modifier: Modifier = Modifier,
    taskList: List<TaskModel>,
) {
    LazyColumn(
        modifier=modifier.fillMaxWidth()
    ) {
        items(
            items = taskList,
            key = { item -> item.taskId },
        ) { item ->
            TaskItem(
                taskModel = item,
            )
        }
    }
}

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    taskModel: TaskModel,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = dp10)
            .padding(horizontal = dp5)
    ) {
        IconButton(
            onClick = {},
        ) {
            Icon(
                painter = painterResource(id = R.drawable.unmarked),
                contentDescription = null,
                tint = if (isSystemInDarkTheme()) Color.White
                else Color.Gray
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = taskModel.taskText,
                    fontSize = sp18,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier=modifier.weight(0.9f).padding(start= dp5)
                )
                Card(
                    modifier = Modifier
                        .size(dp20)
                        .clip(CircleShape),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.parse(
                            taskModel.categoryColor
                        )
                    ),
                ) {
                }
            }
            Row(
            ) {
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
                    text = taskModel.taskDate,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                )
                IconButton(
                    modifier = modifier
                        .size(dp25),
                    onClick = {

                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.alarm),
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
                Text(
                    modifier = modifier.padding(dp4),
                    text = taskModel.taskTime,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                )
            }
            Divider(
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun TaskItemPreview() {
    TaskItem(
        taskModel = TaskModel(
            taskId = "a",
            taskTime = "d",
            taskDate = "a",
            taskText = "A",
            categoryColor = "",
            categoryId = ""
        ),
    )
}
