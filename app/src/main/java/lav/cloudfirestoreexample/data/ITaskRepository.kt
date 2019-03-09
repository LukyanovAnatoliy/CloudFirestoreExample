package lav.cloudfirestoreexample.data

/**
 * Created by Anatoliy Lukyanov on 05/03/2019.
 *
 */
interface ITaskRepository {

    fun getAllTask(): List<Task>

    fun addTask(task: Task)
    fun deletetask(taskId: String)

}