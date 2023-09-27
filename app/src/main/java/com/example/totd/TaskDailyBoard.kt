package com.example.totd

data class TaskDailyBoard(
    val date: String,
    val taskItems: List<TaskItem>,
    var isOpen: Boolean
){
    companion object{

    }
}
