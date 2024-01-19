package com.example.taskertodo.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.taskertodo.R
import com.example.taskertodo.presentation.theme.TaskerBlue

@Preview
@Composable
fun DropDownMenuItemPreview() {
    MaterialTheme {
        DropDownMenu(
            onDismissMenu = {}
        ){

        }
    }
}

@Composable
fun DropDownMenu(
    modifier: Modifier = Modifier,
    onDismissMenu: () -> Unit,
    onClickCallBack: (isTask: Boolean) -> Unit
) {
    DropdownMenu(
        modifier = modifier
            .width(200.dp)
            .background(MaterialTheme.colorScheme.background),
        offset = DpOffset(12.dp,12.dp),
        expanded = true,
        onDismissRequest = {
            onDismissMenu.invoke()
        }
    ) {
        DropDownMenuItem(
            title = R.string.task_txt,
            icon = R.drawable.icon_task,
            isTask = true,
            onClickCallBack = {
                onClickCallBack.invoke(it)
            }
        )
        Divider()
        DropDownMenuItem(
            isTask = false,
            title = R.string.list_txt,
            icon = R.drawable.icon_list,
            onClickCallBack = {
                onClickCallBack.invoke(it)
            }
        )
    }
}

@Composable
fun DropDownMenuItem(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    onClickCallBack:(isTask:Boolean)->Unit,
    isTask:Boolean
) {
    DropdownMenuItem(
        text = {
            Text(
                text = stringResource(id = title),
                color = TaskerBlue
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = TaskerBlue
            )
        },
        onClick = { onClickCallBack.invoke(isTask)}
    )
}