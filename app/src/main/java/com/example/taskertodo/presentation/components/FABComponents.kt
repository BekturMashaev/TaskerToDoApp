package com.example.taskertodo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskertodo.R
import com.example.taskertodo.presentation.theme.TaskerBlue

@Preview
@Composable
fun FABComponentPreview() {
    MaterialTheme {
        FABComponent(
        ){

        }
    }
}

@Composable
fun FABComponent(
    modifier: Modifier = Modifier,
    onDropDownItemCallBack:(isTask:Boolean)->Unit
) {
    var isCLick by remember {
        mutableStateOf(false)
    }
    Column {
        if (isCLick) DropDownMenu(
            onDismissMenu = {
                isCLick=false
            }
        ){onDropDownItemCallBack(it)
        }
        Card(
            shape= CircleShape,
            modifier = modifier
                .size(64.dp),
        ) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(if (isCLick) TaskerBlue else Color.White)
                    .clickable {
                        isCLick = !isCLick
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isCLick) R.drawable.plus_icon
                        else R.drawable.exit_icon
                    ), contentDescription = null,
                    tint = if (isCLick) Color.White else TaskerBlue
                )
            }
        }
    }
}