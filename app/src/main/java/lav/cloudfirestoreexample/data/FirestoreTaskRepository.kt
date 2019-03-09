package lav.cloudfirestoreexample.data

/**
 * Created by Anatoliy Lukyanov on 05/03/2019.
 *
 */
class FirestoreTaskRepository: ITaskRepository {

    override fun getAllTask(): List<Task> {
        return listOf(
            Task("1", "Task1"),
            Task("2", "Task2"),
            Task("3", "Task3"),
            Task("4", "Task4"),
            Task("5", "Task5"),
            Task("6", "Task6"),
            Task("7", "Task7"),
            Task("8", "Task8"),
            Task("9", "Task9"),
            Task("10", "Task10"),
            Task("11", "Task11")
        )
    }

    override fun addTask(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deletetask(taskId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}