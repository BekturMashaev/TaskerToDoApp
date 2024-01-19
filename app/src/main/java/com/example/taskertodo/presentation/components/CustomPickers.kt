package com.example.taskertodo.presentation.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composetaskerapp.presentation.components.InfiniteItemsPicker
import com.example.composetaskerapp.presentation.components.currentDay
import com.example.composetaskerapp.presentation.components.currentHour
import com.example.composetaskerapp.presentation.components.currentMinute
import com.example.composetaskerapp.presentation.components.currentMonth
import com.example.composetaskerapp.presentation.components.currentYear
import com.example.composetaskerapp.presentation.components.days
import com.example.composetaskerapp.presentation.components.hours
import com.example.composetaskerapp.presentation.components.minutes
import com.example.composetaskerapp.presentation.components.monthsNames
import com.example.composetaskerapp.presentation.components.years
import com.example.taskertodo.R
import com.example.taskertodo.presentation.theme.TaskerBlue
import com.example.taskertodo.presentation.theme.dp20
import com.example.taskertodo.presentation.theme.dp30
import com.example.taskertodo.presentation.theme.dp5
import com.example.taskertodo.presentation.theme.sp15
import com.example.taskertodo.presentation.theme.sp26
import java.time.LocalDate
import java.time.LocalTime

@SuppressLint("UnrememberedMutableState")
@Composable
fun CustomDatePicker(
    modifier: Modifier = Modifier,
    onSelectTaskDate: (LocalDate) -> Unit,
) {
    var selectedDay by mutableIntStateOf(currentDay)
    var selectedMonth by mutableIntStateOf(currentMonth)
    var selectedYear by mutableIntStateOf(currentYear)
    Column(
        modifier = modifier.padding(bottom = dp5)
    ) {
        Spacer(modifier = modifier.height(dp30))
        DatePickerUtil(
            height = 200.dp,
            onSelectDateDay = {
                selectedDay = it.toInt()
            },
            onSelectDateMonth = {
                selectedMonth = it
            },
            onSelectDateYear = {
                selectedYear = it.toInt()
            }
        )
        Spacer(modifier = modifier.height(dp20))
        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = dp20),
            onClick = {
                onSelectTaskDate(
                    LocalDate.of(
                        selectedYear,
                        selectedMonth,
                        selectedDay,
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = TaskerBlue
            )
        ) {
            Text(
                text = stringResource(R.string.confirm_btn_txt),
                fontSize = sp15,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun CustomTimePicker(
    modifier: Modifier = Modifier,
    onSelectTaskTime: (LocalTime) -> Unit,
) {
    var selectedMinutes by mutableIntStateOf(currentMinute)
    var selectedHours by mutableIntStateOf(currentHour)
    Column(
        modifier = modifier.padding(bottom = dp5)
    ) {
        Spacer(modifier = modifier.height(dp30))
        TimePickerUtil(
            height = 200.dp,
            onSelectHours = {
                selectedHours = it.toInt()
            },
            onSelectMinutes = {
                selectedMinutes = it.toInt()
            }
        )
        Spacer(modifier = modifier.height(dp20))
        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = dp20),
            onClick = {
                onSelectTaskTime(
                    LocalTime.of(
                        selectedHours,
                        selectedMinutes,
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = TaskerBlue
            )
        ) {
            Text(
                text = stringResource(R.string.confirm_btn_txt),
                fontSize = sp15,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Composable
fun DatePickerUtil(
    modifier: Modifier = Modifier,
    height: Dp,
    onSelectDateDay: (String) -> Unit,
    onSelectDateMonth: (Int) -> Unit,
    onSelectDateYear: (String) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
            .height(height),
    ) {
        InfiniteItemsPicker(
            items = days,
            firstIndex = 0,
            onItemSelected = {
                onSelectDateDay(it)
            },
            textStyle = MaterialTheme.typography.titleLarge.copy(
                fontSize = sp26,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        )
        InfiniteItemsPicker(
            items = monthsNames,
            firstIndex = 0,
            onItemSelected = {
                onSelectDateMonth(parseMonthNamesToInt(it))
            },
            textStyle = MaterialTheme.typography.titleLarge.copy(
                fontSize = sp26,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        )
        InfiniteItemsPicker(
            items = years,
            firstIndex = 0,
            onItemSelected = {
                onSelectDateYear(it)
            },
            textStyle = MaterialTheme.typography.titleLarge.copy(
                fontSize = sp26,
                fontWeight = FontWeight.Medium,
                color = Color.White,
            )
        )
    }
}

@Composable
fun TimePickerUtil(
    onSelectHours: (String) -> Unit,
    onSelectMinutes: (String) -> Unit,
    modifier: Modifier = Modifier,
    height: Dp,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
            .height(height),
    ) {
        InfiniteItemsPicker(
            items = hours,
            firstIndex = 0,
            onItemSelected = {
                onSelectHours(it)
            },
            textStyle = MaterialTheme.typography.titleLarge.copy(
                fontSize = sp26,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        )
        InfiniteItemsPicker(
            items = minutes,
            firstIndex = 0,
            onItemSelected = {
                onSelectMinutes(it)
            },
            textStyle = MaterialTheme.typography.titleLarge.copy(
                fontSize = sp26,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        )
    }
}

fun parseMonthNamesToInt(monthName: String): Int {
    return monthName.indexOf(monthName)
}