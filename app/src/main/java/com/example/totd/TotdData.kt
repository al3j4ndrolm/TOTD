package com.example.totd

import androidx.compose.ui.graphics.Color

data class TotdData(
    val taskDailyBoards: MutableList<TaskDailyBoard> = mutableListOf(),
    val taskLabels: MutableList<TaskLabel> = mutableListOf(),
){
    fun addDummyData(){
        val taskLabelMath =
            TaskLabel(labelName = "MATH 31", labelColor = Color.hsl(hue = 203f, 1.0f, .40f))
        val taskLabelEnglish =
            TaskLabel(labelName = "ESL 5", labelColor = Color.hsl(hue = 26f, 1.0f, .40f))
        val taskLabelArts =
            TaskLabel(labelName = "ARTS 1A", labelColor = Color.hsl(269f, 1.0f, .50f))

        taskLabels.add(taskLabelMath)
        taskLabels.add(taskLabelEnglish)
        taskLabels.add(taskLabelArts)

        taskDailyBoards.addAll(
            listOf(
                TaskDailyBoard(
                    date = "Today",
                ),
                TaskDailyBoard(
                    date = "Sep 27, 2023",
                ),
                TaskDailyBoard(
                    date = "Sep 30, 2023",
                ),
                TaskDailyBoard(
                    date = "Oct 2, 2023",
                ),
                TaskDailyBoard(
                    date = "Oct 3, 2023",
                )
            )
        )

        taskDailyBoards[0].addNewTask(
            taskItem = TaskItem(
                taskItemLabel = taskLabelMath,
                taskItemDetails = "Review Polynomial problems"
            )
        )
        taskDailyBoards[0].addNewTask(
            taskItem = TaskItem(
                taskItemLabel = taskLabelMath,
                taskItemDetails = "Practice multiplication with YY"
            )
        )
        taskDailyBoards[0].addNewTask(
            taskItem = TaskItem(
                taskItemLabel = taskLabelArts,
                taskItemDetails = "Analyze the 19th century art"
            )
        )
        taskDailyBoards[0].addNewTask(
            taskItem = TaskItem(
                taskItemLabel = taskLabelEnglish,
                taskItemDetails = "Write an essay about the US immigration history"
            )
        )
        taskDailyBoards[1].addNewTask(
            taskItem = TaskItem(
                taskItemLabel = taskLabelMath,
                taskItemDetails = "Finish homework for Range and Domain"
            )
        )
        taskDailyBoards[1].addNewTask(
            taskItem = TaskItem(
                taskItemLabel = taskLabelArts,
                taskItemDetails = "Quiz - The art and the different types of expressions"
            )
        )
        taskDailyBoards[2].addNewTask(
            taskItem = TaskItem(
                taskItemLabel = taskLabelEnglish,
                taskItemDetails = "Write and essay about the immigration in United States"
            )
        )
        taskDailyBoards[2].addNewTask(
            taskItem = TaskItem(
                taskItemLabel = taskLabelEnglish,
                taskItemDetails = "Read the textbook (Page 23-34)"
            )
        )
        taskDailyBoards[2].addNewTask(
            taskItem = TaskItem(
                taskItemLabel = taskLabelEnglish,
                taskItemDetails = "Write the analysis about the textbook and submit the extra-credits"
            )
        )
    }
}
