package com.example.totd

data class TaskItem(
    val taskItemLabel: TaskLabel,
    val taskItemDetails: String,
    var isDone: Boolean = false
) {
    companion object
}
