package lav.cloudfirestoreexample.data

import io.reactivex.Single

/**
 * Created by Anatoliy Lukyanov on 05/03/2019.
 *
 */
interface ITaskRepository {

    fun getAllTask(): Single<List<Task>>

    fun addTask(task: Task)
    fun deleteTask(taskId: String)

}