package lav.cloudfirestoreexample.data.remote

import com.google.firebase.Timestamp
import lav.cloudfirestoreexample.data.Task

/**
 * Created by Anatoliy Lukyanov on 10/03/2019.
 *
 */
fun mapToTask(remoteTask: RemoteTask): Task {
    return Task(
        remoteTask.id,
        remoteTask.title,
        remoteTask.created.toDate()
    )
}

fun mapToRemoteTask(task: Task): RemoteTask {
    return RemoteTask(
        task.id,
        task.title,
        Timestamp(task.created.time / 1000, (task.created.time % 1000 * 1000).toInt())
    )
}