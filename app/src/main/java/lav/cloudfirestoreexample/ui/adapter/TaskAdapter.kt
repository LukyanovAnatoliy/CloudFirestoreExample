package lav.cloudfirestoreexample.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recycler_item.view.*
import lav.cloudfirestoreexample.R
import lav.cloudfirestoreexample.data.Task

/**
 * Created by Anatoliy Lukyanov on 06/03/2019.
 *
 */
class TaskAdapter(private val taskClickListener: OnTaskClickListener?) : RecyclerView.Adapter<TaskViewHolder>() {

    private val taskList = emptyList<Task>().toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        view.deleteTask.setOnClickListener { button -> taskClickListener?.run { this.onDeleteClick(button.tag as String) } }
        return TaskViewHolder(view)
    }


    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        with(holder.containerView) {
            taskTitle.text = task.title
            deleteTask.tag = taskList[position].id
        }
    }

    fun setItems(newTaskList: List<Task>) {
        val diffResult  = DiffUtil.calculateDiff(TaskDiffUtilCallback(taskList, newTaskList))

        taskList.clear()
        taskList.addAll(newTaskList)

        diffResult.dispatchUpdatesTo(this)
    }
}

class TaskViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer

interface OnTaskClickListener {
    fun onDeleteClick(taskId: String)
}