package lav.cloudfirestoreexample.data.remote

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