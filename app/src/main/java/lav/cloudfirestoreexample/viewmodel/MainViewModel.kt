package lav.cloudfirestoreexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lav.cloudfirestoreexample.data.FirestoreTaskRepository
import lav.cloudfirestoreexample.data.ITaskRepository
import lav.cloudfirestoreexample.data.Task

/**
 * Created by Anatoliy Lukyanov on 05/03/2019.
 *
 */
class MainViewModel: ViewModel() {

    private val _taskList = MutableLiveData<List<Task>>()
    val taskList: LiveData<List<Task>>
        get() = _taskList

    init {
        val repository: ITaskRepository = FirestoreTaskRepository()

        _taskList.value = repository.getAllTask()
    }

    fun deleteTask(taskId: String) {
        val newList: MutableList<Task> = _taskList.value!!.toMutableList()
        newList.removeAll { it.id == taskId }
        _taskList.value = newList
    }

    fun addTask(taskTitle: String) {
        val newTask = Task("${System.currentTimeMillis()}", taskTitle)
        val newList: MutableList<Task> = _taskList.value!!.toMutableList()
        newList.add(newTask)
        _taskList.value = newList
    }

}