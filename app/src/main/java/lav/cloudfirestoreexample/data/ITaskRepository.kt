package lav.cloudfirestoreexample.data

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Anatoliy Lukyanov on 05/03/2019.
 *
 */
interface ITaskRepository {

    fun getAllTask(): Single<List<Task>>

    fun addTask(task: Task): Completable
    fun deleteTask(taskId: String): Completable

    fun getChangeObservable(): Observable<List<Task>>
}