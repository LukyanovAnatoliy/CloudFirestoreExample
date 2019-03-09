package lav.cloudfirestoreexample.data

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
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
        }
            .observeOn(Schedulers.io())
            .flatMapObservable { Observable.fromIterable(it) }
            .map { document ->
                document.toObject(RemoteTask::class.java)!!.apply { id = document.id }
            }
            .map(::mapToTask)
            .toList()
    }

    override fun addTask(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteTask(taskId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}