package lav.cloudfirestoreexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import lav.cloudfirestoreexample.addTo
import lav.cloudfirestoreexample.data.FirestoreTaskRepository
import lav.cloudfirestoreexample.data.ITaskRepository
import lav.cloudfirestoreexample.data.Task

/**
 * Created by Anatoliy Lukyanov on 05/03/2019.
 *
 */
class MainViewModel : ViewModel() {

    private val _taskList = MutableLiveData<List<Task>>()
    val taskList: LiveData<List<Task>>
        get() = _taskList

    private val repository: ITaskRepository = FirestoreTaskRepository()

    private val disposable = CompositeDisposable()

    init {
        repository.getChangeObservable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    _taskList.value = it
                },
                {
                    it.printStackTrace()
                }
            )
            .addTo(disposable)
    }

    fun deleteTask(taskId: String) {
        repository.deleteTask(taskId)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {},
                {
                    it.printStackTrace()
                })
            .addTo(disposable)
    }

    fun addTask(taskTitle: String) {
        repository.addTask(Task("${System.currentTimeMillis()}", taskTitle))
            .subscribeOn(Schedulers.io())
            .subscribe(
                {},
                {
                    it.printStackTrace()
                })
            .addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}