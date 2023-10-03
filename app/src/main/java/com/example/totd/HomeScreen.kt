package com.example.totd

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
    private val taskDailyBoards: MutableList<TaskDailyBoard> = mutableListOf()

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
        Column {
            for (taskDailyBoard in taskDailyBoards) {
                DrawTaskDailyBoard(taskDailyBoard = taskDailyBoard)
            }
            DrawNoTaskWarning()
        }
    }

    @Composable
    fun DrawNoTaskWarning() {
        Text(text = "Oops! Theres no tasks yet!")
    }

    @Composable
    fun DrawTaskDailyBoard(taskDailyBoard: TaskDailyBoard) {
        var isOpen by remember {
            mutableStateOf(taskDailyBoard.isOpen)
        }
        if (taskDailyBoard.hasActiveTasks()) {
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
                    var isDone by remember {
                        mutableStateOf(taskItem.isDone)
                    }

                    if (!isDone) {
                        DrawTaskItemBlock(taskItem = taskItem,
                            onCheckHandle = {
                                taskItem.isDone = true
                                isDone = taskItem.isDone
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
    fun DrawTaskItemBlock(taskItem: TaskItem, onCheckHandle: () -> Unit) {
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
            Box(modifier = Modifier.fillMaxWidth()) {
                DrawTaskItemContent(taskItem)
            }
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
            DrawTaskLabel(taskLabel = taskItem.taskItemLabel)
            Text(text = taskItem.taskItemDetails, color = Color.DarkGray)
        }
    }

    @Composable
    fun DrawTaskLabel(taskLabel: TaskLabel) {
        Box(
            modifier = Modifier
                .background(taskLabel.labelColor)
                .clip(RoundedCornerShape(8.dp))
                .padding(1.dp)
        ) {
            Text(
                text = taskLabel.labelName,
                color = Color.White
            )
        }
    }


    companion object {
        fun create(): HomeScreen {
            val homeScreen = HomeScreen()
            val taskLabelMath =
                TaskLabel(labelName = "MATH 31", labelColor = Color.hsl(hue = 203f, 1.0f, .40f))
            val taskLabelEnglish =
                TaskLabel(labelName = "ESL 5", labelColor = Color.hsl(hue = 26f, 1.0f, .40f))
            val taskLabelArts =
                TaskLabel(labelName = "ARTS 1A", labelColor = Color.hsl(269f, 1.0f, .50f))

            homeScreen.taskDailyBoards.addAll(
                listOf(
                    TaskDailyBoard(
                        date = "Today",
                        isOpen = true
                    ),
                    TaskDailyBoard(
                        date = "Sep 27, 2023",
                        isOpen = true
                    ),
                    TaskDailyBoard(
                        date = "Sep 30, 2023",
                        isOpen = false
                    ),
                    TaskDailyBoard(
                        date = "Oct 2, 2023",
                        isOpen = false
                    ),
                    TaskDailyBoard(
                        date = "Oct 3, 2023",
                        isOpen = false
                    )
                )
            )

            homeScreen.taskDailyBoards[0].addNewTask(
                taskItem = TaskItem(
                    taskItemLabel = taskLabelMath,
                    taskItemDetails = "Review Polynomial problems"
                )
            )
            homeScreen.taskDailyBoards[0].addNewTask(
                taskItem = TaskItem(
                    taskItemLabel = taskLabelMath,
                    taskItemDetails = "Practice multiplication with YY"
                )
            )
            homeScreen.taskDailyBoards[0].addNewTask(
                taskItem = TaskItem(
                    taskItemLabel = taskLabelArts,
                    taskItemDetails = "Analyze the 19th century art"
                )
            )
            homeScreen.taskDailyBoards[0].addNewTask(
                taskItem = TaskItem(
                    taskItemLabel = taskLabelEnglish,
                    taskItemDetails = "Write an essay about the US immigration history"
                )
            )
            homeScreen.taskDailyBoards[1].addNewTask(
                taskItem = TaskItem(
                    taskItemLabel = taskLabelMath,
                    taskItemDetails = "Finish homework for Range and Domain"
                )
            )
            homeScreen.taskDailyBoards[1].addNewTask(
                taskItem = TaskItem(
                    taskItemLabel = taskLabelArts,
                    taskItemDetails = "Quiz - The art and the different types of expressions"
                )
            )
            homeScreen.taskDailyBoards[2].addNewTask(
                taskItem = TaskItem(
                    taskItemLabel = taskLabelEnglish,
                    taskItemDetails = "Write and essay about the immigration in United States"
                )
            )
            homeScreen.taskDailyBoards[2].addNewTask(
                taskItem = TaskItem(
                    taskItemLabel = taskLabelEnglish,
                    taskItemDetails = "Read the textbook (Page 23-34)"
                )
            )
            homeScreen.taskDailyBoards[2].addNewTask(
                taskItem = TaskItem(
                    taskItemLabel = taskLabelEnglish,
                    taskItemDetails = "Write the analysis about the textbook and submit the extra-credits"
                )
            )

            return homeScreen
        }
    }
}