package lav.cloudfirestoreexample.data

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import lav.cloudfirestoreexample.data.remote.RemoteTask
import lav.cloudfirestoreexample.data.remote.mapToTask

/**
 * Created by Anatoliy Lukyanov on 05/03/2019.
 *
 */
class FirestoreTaskRepository : ITaskRepository {

    companion object {
        private const val TASKS_COLLECTION = "Tasks"
        private const val TASK_FIELD_TITLE = "title"
        private const val TASK_FIELD_CREATED = "created"
    }

    private val remoteDB = FirebaseFirestore.getInstance().apply {
        firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()
    }

    override fun getAllTask(): Single<List<Task>> {
        return Single.create<List<DocumentSnapshot>> { emitter ->
            remoteDB.collection(TASKS_COLLECTION)
                .get()
                .addOnSuccessListener {
                    if (!emitter.isDisposed) {
                        emitter.onSuccess(it.documents)
                    }
                }
                .addOnFailureListener {
                    if (!emitter.isDisposed) {
                        emitter.onError(it)
                    }
                }
        }
            .observeOn(Schedulers.io())
            .flatMapObservable { Observable.fromIterable(it) }
            .map { document ->
                document.toObject(RemoteTask::class.java)!!.apply { id = document.id }
            }
            .map(::mapToTask)
            .toList()
    }

    override fun addTask(task: Task): Completable {
        return Completable.create { emitter ->

            val taskData = HashMap<String, Any>()
            taskData[TASK_FIELD_TITLE] = task.title
            taskData[TASK_FIELD_CREATED] = Timestamp(task.created.time / 1000, (task.created.time % 1000 * 1000).toInt())

            remoteDB.collection(TASKS_COLLECTION)
                .add(taskData)
                .addOnSuccessListener {
                    if (!emitter.isDisposed) {
                        emitter.onComplete()
                    }
                }
                .addOnFailureListener {
                    if (!emitter.isDisposed) {
                        emitter.onError(it)
                    }
                }
        }
    }

    override fun deleteTask(taskId: String): Completable {
        return Completable.create { emitter ->

            remoteDB.collection(TASKS_COLLECTION)
                .document(taskId)
                .delete()
                .addOnSuccessListener {
                    if (!emitter.isDisposed) {
                        emitter.onComplete()
                    }
                }
                .addOnFailureListener {
                    if (!emitter.isDisposed) {
                        emitter.onError(it)
                    }
                }
        }
    }
}