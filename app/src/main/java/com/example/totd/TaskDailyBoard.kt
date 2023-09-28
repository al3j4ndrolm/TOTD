package com.example.totd

data class TaskDailyBoard(
    val date: String,
    val taskItems: List<TaskItem>,
    var isOpen: Boolean
){
    fun hasActiveTasks() : Boolean{
        return taskItems.any { !it.isDone }
    }

    fun getActiveTasksCount(): Int {
        return taskItems.filter { !it.isDone }.size
    }

    companion object{

    }
}
