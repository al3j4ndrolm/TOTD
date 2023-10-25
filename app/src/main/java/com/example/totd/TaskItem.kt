package com.example.totd

data class TaskItem(
    val taskItemLabel: TaskLabel,
    var taskItemDetails: String,
    var taskItemDetailsDraft: String = "",
    var isDone: Boolean = false,
) {
    companion object
}
