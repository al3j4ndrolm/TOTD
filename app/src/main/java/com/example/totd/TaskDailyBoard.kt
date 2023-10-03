package com.example.totd

data class TaskDailyBoard(
    val date: String,
    val taskItems: MutableList<TaskItem> = mutableListOf(),
    var isOpen: Boolean
){
    fun hasActiveTasks() : Boolean{
        return taskItems.any { !it.isDone }
    }

    fun getActiveTasksCount(): Int {
        return taskItems.filter { !it.isDone }.size
    }

    fun addNewTask(taskItem: TaskItem){
        taskItems.add(taskItem)
    }

    companion object{

    }
}
