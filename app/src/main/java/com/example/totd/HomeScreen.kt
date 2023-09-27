package com.example.totd

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class HomeScreen {
    @Composable
    fun launch() {
        Column {
            createHeader()
            createTaskList()
        }
    }

    @Composable
    fun createHeader() {
        Row(
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxWidth(),
            verticalAlignment = CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "",
                modifier = Modifier.size(100.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                Column(
                    modifier = Modifier.size(width = 200.dp, height = 80.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Tails",
                        fontFamily = FontFamily(Font(resId = R.font.capriola)),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                    Text(
                        text = "2 Task Today",
                        fontFamily = FontFamily(Font(resId = R.font.capriola)),
                        fontSize = 18.sp,
                        color = Color.LightGray
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(60.dp)
                ) {
                    Icon(
                        Icons.Default.Menu, "", modifier = Modifier.size(40.dp),
                        tint = Color.LightGray
                    )
                }
            }
        }
    }

    @Composable
    fun createTaskList() {

        val taskLabelMath =
            TaskLabel(labelName = "MATH 31", labelColor = Color.hsl(hue = 203f, 1.0f, .40f))
        val taskLabelEnglish =
            TaskLabel(labelName = "ESL 5", labelColor = Color.hsl(hue = 26f, 1.0f, .40f))

        val taskDailyBoard1 =
            TaskDailyBoard(
                date = "Today",
                taskItems = listOf(
                    TaskItem(
                        taskItemLabel = taskLabelMath,
                        taskItemDetails = "Finish homework 1.1 and 1.2"
                    ),
                    TaskItem(
                        taskItemLabel = taskLabelMath,
                        taskItemDetails = "Finish homework 1.1 and 1.2 and more and more and more and more"
                    ),
                    TaskItem(
                        taskItemLabel = taskLabelEnglish,
                        taskItemDetails = "Write a story about my family in Venezuela"
                    )
                ),
                isOpen = true
            )
        val taskDailyBoard2 =
            TaskDailyBoard(
                date = "Sep 27, 2023",
                taskItems = listOf(
                    TaskItem(
                        taskItemLabel = taskLabelMath,
                        taskItemDetails = "Finish homework 1.5"
                    ),
                    TaskItem(
                        taskItemLabel = taskLabelEnglish,
                        taskItemDetails = "Listen to podcast"
                    )
                ),
                isOpen = true
            )
        val taskDailyBoard3 =
            TaskDailyBoard(
                date = "Sep 30, 2023",
                taskItems = listOf(
                    TaskItem(taskItemLabel = taskLabelMath, taskItemDetails = "Practice equation"),
                ),
                isOpen = true
            )

        Column {
            drawTaskDailyBoard(taskDailyBoard = taskDailyBoard1)
            drawTaskDailyBoard(taskDailyBoard = taskDailyBoard2)
            drawTaskDailyBoard(taskDailyBoard = taskDailyBoard3)
        }
    }

    @Composable
    fun drawTaskDailyBoard(taskDailyBoard: TaskDailyBoard) {
        drawDateHeader(
            dateText = taskDailyBoard.date,
            taskItemCount = taskDailyBoard.taskItems.size
        )

        if (taskDailyBoard.isOpen) {
            for (taskItem in taskDailyBoard.taskItems) {
                drawTaskItemBlock(taskItem = taskItem)
            }
        } else {
            Box(
                modifier = Modifier
                    .size(2.dp)
                    .fillMaxWidth()
            )
        }
    }

    @Composable
    fun drawDateHeader(dateText: String, taskItemCount: Int) {
        Box {
            Text(
                text = dateText,
                fontFamily = FontFamily(Font(resId = R.font.capriola)),
                color = Color.Gray,
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(vertical = 4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = taskItemCount.toString(),
                modifier = Modifier
                    .padding(end = 12.dp)
                    .align(CenterEnd),
                fontFamily = FontFamily(Font(resId = R.font.capriola)),
                color = Color.Gray,
                textAlign = TextAlign.Right
            )
        }
    }

    @Composable
    fun drawTaskItemBlock(taskItem: TaskItem) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.LightGray),
            verticalAlignment = CenterVertically
        ) {
            Box(
                modifier = Modifier.size(80.dp),
                contentAlignment = Center
            ) {
                drawTaskItemCheckbox()
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                drawTaskItemContent(taskItem)
            }
        }
    }

    @Composable
    fun drawTaskItemCheckbox() {
        var isChecked by remember { mutableStateOf(false) }

        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            modifier = Modifier.padding(16.dp),
            colors = CheckboxDefaults.colors(
                uncheckedColor = Color.Gray,
                checkedColor = Color.LightGray,
                checkmarkColor = Color.DarkGray,
                disabledCheckedColor = Color.LightGray,
                disabledUncheckedColor = Color.LightGray,
                disabledIndeterminateColor = Color.LightGray
            )
        )
    }

    @Composable
    fun drawTaskItemContent(taskItem: TaskItem) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            drawTaskLabel(taskLabel = taskItem.taskItemLabel)
            Text(text = taskItem.taskItemDetails, color = Color.DarkGray)
        }
    }

    @Composable
    fun drawTaskLabel(taskLabel: TaskLabel) {
        Box(
            modifier = Modifier
                .background(taskLabel.labelColor)
                .clip(RoundedCornerShape(8.dp))
                .padding(2.dp)
        ) {
            Text(
                text = taskLabel.labelName,
                color = Color.White
            )
        }
    }


    companion object {
        fun create(): HomeScreen {
            return HomeScreen()
        }
    }
}