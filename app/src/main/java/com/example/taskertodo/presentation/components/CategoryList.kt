package com.example.taskertodo.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskertodo.data.models.CategoryModel
import com.example.taskertodo.presentation.theme.dp0
import com.example.taskertodo.presentation.theme.dp12
import com.example.taskertodo.presentation.theme.dp14
import com.example.taskertodo.presentation.theme.dp20
import com.example.taskertodo.presentation.theme.dp28
import com.example.taskertodo.presentation.theme.dp5
import com.example.taskertodo.presentation.theme.dp70
import com.example.taskertodo.presentation.theme.sp19

@Preview
@Composable
fun CategoryListPreview() {
    MaterialTheme {
        CategoryList(
            categoryList = listOf(
                CategoryModel(
                    id = "1",
                    title = "Aha",
                    color = "",
                ),
            ),
            selectCategory = {},
            isListForMainScreen = true
        )
    }
}

@Composable
fun CategoryList(
    modifier: Modifier = Modifier,
    categoryList: List<CategoryModel>,
    selectCategory: (CategoryModel) -> Unit,
    isListForMainScreen: Boolean
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(
            items = categoryList,
            key = { item -> item.id }
        ) { item ->
            CategoryItem(
                model = item,
                onSelectCategory = selectCategory,
                isListForMainScreen = isListForMainScreen
            )
        }
    }
}

@Composable
fun CategoryItem(
    model: CategoryModel,
    onSelectCategory: (CategoryModel) -> Unit,
    isListForMainScreen: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = if (isListForMainScreen) Alignment.End
        else Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.parse(model.color)),
            modifier = Modifier
                .fillMaxWidth(
                    if (isListForMainScreen) 0.9f
                    else 1f
                )
                .height(dp70)
                .padding(dp5)
                .clickable {
                    onSelectCategory(model)
                }
        ) {
            Box(
                modifier = modifier.fillMaxSize(),
            ) {
                Column {
                    Text(
                        text = model.title,
                        modifier = modifier.padding(top = dp12, start = dp14),
                        fontSize = sp19,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = modifier.weight(1f))
                    IconButton(
                        onClick = {},
                        modifier = modifier.padding(top = dp20, end = dp20, bottom = dp20)
                    ) {
                        CircleColorComponent(
                            modifier = modifier
                                .size(dp28),
                            color = Color.White
                        )
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

fun Color.Companion.parse(colorString: String): Color =
    Color(color = android.graphics.Color.parseColor(colorString))