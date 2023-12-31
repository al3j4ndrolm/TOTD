package com.example.totd

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class HomeScreen {
    val totdData: TotdData = TotdData()
    val taskInfoScreen: TaskInfoScreen = TaskInfoScreen()

    init {
        totdData.addDummyData()
    }

    @Composable
    fun Launch() {
        Column {
            DrawAppHeader()
            DrawTaskBoard()
        }
    }

    @Composable
    fun DrawAppHeader() {
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
    fun DrawTaskBoard() {
        var hasAnyActiveTask by remember {
            mutableStateOf(totdData.taskDailyBoards.any { it.hasActiveTasks() })
        }
        Column {
            for (taskDailyBoard in totdData.taskDailyBoards) {
                DrawTaskDailyBoard(
                    taskDailyBoard = taskDailyBoard,
                    refreshNoTaskWarning = {
                        hasAnyActiveTask = totdData.taskDailyBoards.any { it.hasActiveTasks() }
                    })
            }
            if (!hasAnyActiveTask) {
                DrawNoTaskWarning()
            }
        }
    }

    @Composable
    fun DrawNoTaskWarning() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.android_logo),
                    contentDescription = "",
                    alpha = 0.4f,
                    modifier = Modifier.size(300.dp, 125.dp)
                )
                Text(
                    text = "Oops! Theres no tasks yet!", color = Color.Gray,
                    fontFamily = FontFamily(Font(resId = R.font.capriola)),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 100.dp)
                )
            }
        }


    }

    @Composable
    fun DrawTaskDailyBoard(taskDailyBoard: TaskDailyBoard, refreshNoTaskWarning: () -> Unit) {
        var isOpen by remember {
            mutableStateOf(taskDailyBoard.isOpen)
        }
        var activeTaskCount by remember {
            mutableStateOf(taskDailyBoard.getActiveTasksCount())
        }

        if (activeTaskCount > 0) {
            DrawDateHeader(
                dateText = taskDailyBoard.date,
                taskItemCount = taskDailyBoard.getActiveTasksCount(),
                onClickHandle = {
                    taskDailyBoard.isOpen = !taskDailyBoard.isOpen
                    isOpen = taskDailyBoard.isOpen
                }
            )

            if (isOpen) {
                for (taskItem in taskDailyBoard.taskItems) {
                    if (!taskItem.isDone) {
                        DrawTaskItemBlock(taskItem = taskItem,
                            onCheckHandle = {
                                taskItem.isDone = !taskItem.isDone
                                refreshNoTaskWarning()
                                activeTaskCount = taskDailyBoard.getActiveTasksCount()
                            },
                            onDeleteTaskItem = {
                                taskDailyBoard.taskItems.remove(taskItem)
                                refreshNoTaskWarning()
                                activeTaskCount = taskDailyBoard.getActiveTasksCount()
                            })
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .size(2.dp)
                        .fillMaxWidth()
                )
            }
        }
    }

    @Composable
    fun DrawDateHeader(dateText: String, taskItemCount: Int, onClickHandle: () -> Unit) {
        Box(
            modifier = Modifier.clickable { onClickHandle() }
        ) {
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
    fun DrawTaskItemBlock(
        taskItem: TaskItem,
        onCheckHandle: () -> Unit,
        onDeleteTaskItem: () -> Unit
    ) {
        var shouldLaunchTaskInfoScreen by remember {
            mutableStateOf(false)
        }

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
                DrawTaskItemCheckbox(
                    onCheckHandle = onCheckHandle
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        shouldLaunchTaskInfoScreen = true
                    },
            ) {
                DrawTaskItemContent(taskItem)
            }
        }

        if (shouldLaunchTaskInfoScreen) {
            taskInfoScreen.Launch(
                taskItem = taskItem,
                onCloseTaskInfoScreen = { shouldLaunchTaskInfoScreen = false },
                onDeleteTaskItem = { onDeleteTaskItem() })
        }
    }

    @Composable
    fun DrawTaskItemCheckbox(onCheckHandle: () -> Unit) {
        var isChecked by remember { mutableStateOf(false) }


        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                isChecked = it
                onCheckHandle()
            },
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
    fun DrawTaskItemContent(taskItem: TaskItem) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            taskItem.taskItemLabel.DrawTaskLabel()
            Text(text = taskItem.taskItemDetails, color = Color.DarkGray)
        }
    }
}