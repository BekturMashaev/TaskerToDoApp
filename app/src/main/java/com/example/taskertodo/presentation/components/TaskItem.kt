package com.example.taskertodo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.taskertodo.presentation.theme.dp16
import com.example.taskertodo.data.models.TaskModel

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    taskModel: TaskModel,
    onClick: (TaskModel) -> Unit,
    onSelected: (TaskModel, Boolean) -> Unit,
    isSelected: Boolean
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dp16)
            .background(MaterialTheme.colorScheme.onBackground)
    ) {
        IconButton(
            onClick = { /*TODO*/ }) {
            val unCheckedColor = if (isSystemInDarkTheme()) Color.White
            else Color.Gray
            Icon(
                imageVector =if (isSelected) Icons.Default.CheckCircle
                else Icons.Default.Check,
                contentDescription = null,
                tint = if (isSelected) Color.Blue
                else unCheckedColor
            )
        }
    }
}