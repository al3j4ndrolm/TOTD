package com.example.totd

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

class TaskInfoScreen {
    @Composable
    fun Launch(
        taskItem: TaskItem,
        onCloseTaskInfoScreen: () -> Unit,
        onDeleteTaskItem: () -> Unit

    ) {
        DialogWithImage(
            taskItem = taskItem,
            onCloseTaskInfoScreen = { onCloseTaskInfoScreen() },
            onDeleteTaskItem = { onDeleteTaskItem() }
        )
        taskItem.taskItemDetailsDraft = taskItem.taskItemDetails
    }

    @Composable
    fun DialogWithImage(
        taskItem: TaskItem,
        onCloseTaskInfoScreen: () -> Unit,
        onDeleteTaskItem: () -> Unit
    ) {
        Dialog(onDismissRequest = { onCloseTaskInfoScreen() }) {
            // Draw a rectangle shape with rounded corners inside the dialog
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(375.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    DrawTaskInfo(taskItem)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        TextButton(
                            onClick = {
                                onCloseTaskInfoScreen()
                                onDeleteTaskItem()
                            },
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Text("Delete")
                        }
                        TextButton(
                            onClick = {
                                onCloseTaskInfoScreen()
                                taskItem.taskItemDetails = taskItem.taskItemDetailsDraft
                            },
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Text("Confirm")
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun DrawTaskInfo(taskItem: TaskItem) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Task Info",
                modifier = Modifier.padding(16.dp),
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Date",
                )
                Text(text = "02/04/2023")
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Label",
                )
                taskItem.taskItemLabel.DrawTaskLabel()
            }
            DrawTaskDetailsTextField(taskItem)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DrawTaskDetailsTextField(taskItem: TaskItem) {
        var text by remember { mutableStateOf(taskItem.taskItemDetails) }

        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                taskItem.taskItemDetailsDraft = text
            },
            label = { Text("Details") }
        )
    }
}