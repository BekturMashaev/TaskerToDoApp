package com.example.taskertodo.presentation.screens.screen_add_category

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.taskertodo.R
import com.example.taskertodo.presentation.theme.EMPTY_STRING
import com.example.taskertodo.presentation.theme.TaskerBlue
import com.example.taskertodo.presentation.theme.TaskerGray
import com.example.taskertodo.presentation.theme.dp20
import com.example.taskertodo.presentation.theme.sp18
import com.example.taskertodo.presentation.theme.toHexCode
import com.godaddy.android.colorpicker.HsvColor
import com.godaddy.android.colorpicker.harmony.ColorHarmonyMode
import com.godaddy.android.colorpicker.harmony.HarmonyColorPicker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddCategoryScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    updateCategoryTitle: (String) -> Unit,
    updateCategoryColor: (String) -> Unit,
    saveNewCategory: () -> Unit,
) {
    var textFieldState by remember {
        mutableStateOf(EMPTY_STRING)
    }
    var harmonyColor by mutableStateOf(HsvColor.from(Color.Red))
    var color by mutableStateOf("")
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = {
                TextButton(onClick = {
                    navController.popBackStack()
                }) {
                    Text(
                        text = stringResource(id = R.string.cancel_title_txt),
                        fontSize = sp18,
                        color = TaskerBlue,
                        letterSpacing = (-0.43).sp,
                        fontWeight = FontWeight(400)
                    )
                }
            }, actions = {
                TextButton(onClick = {
                    if (color.isEmpty())
                        Toast.makeText(
                            context,
                            context.getString(R.string.select_color),
                            Toast.LENGTH_SHORT
                        ).show()
                    else if (textFieldState.isEmpty())
                        Toast.makeText(
                            context,
                            context.getString(R.string.fill_in_all_text),
                            Toast.LENGTH_SHORT
                        ).show()
                    else{
                        saveNewCategory()
                        navController.popBackStack()
                    }
                }) {
                    Text(
                        modifier = modifier.padding(end = 16.dp),
                        text = stringResource(R.string.done_title_txt),
                        fontSize = sp18,
                        color = TaskerBlue,
                        letterSpacing = (-0.43).sp,
                        fontWeight = FontWeight(600)
                    )
                }
            })
        },
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    modifier = modifier.fillMaxWidth(0.8f),
                    value = textFieldState,
                    onValueChange = {
                        textFieldState = it
                        updateCategoryTitle(textFieldState)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ), placeholder = {
                        Text(
                            text = stringResource(R.string.what_do_you_want_to_do_txt),
                            fontSize = sp18,
                            fontWeight = FontWeight(500),
                            color = if (isSystemInDarkTheme()) Color.White
                            else TaskerGray
                        )
                    })
                Spacer(modifier = modifier.weight(1f))
                HarmonyColorPicker(
                    modifier = modifier
                        .size(400.dp)
                        .padding(dp20),
                    harmonyMode = ColorHarmonyMode.ANALOGOUS,
                    color = harmonyColor,
                    onColorChanged = {
                        harmonyColor = it
                        color = harmonyColor.toColor().toHexCode()
                        updateCategoryColor(color)
                    },
                    showBrightnessBar = false,
                )
                Spacer(modifier = modifier.weight(1f))
            }
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview
@androidx.compose.runtime.Composable
fun AddCategoryScreenPreview() {
    AddCategoryScreen(
        navController = rememberNavController(),
        updateCategoryTitle = {},
        updateCategoryColor = {},
        saveNewCategory = {

        },
    )
}