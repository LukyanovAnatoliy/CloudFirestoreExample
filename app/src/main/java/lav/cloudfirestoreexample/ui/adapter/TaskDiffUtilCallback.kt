package lav.cloudfirestoreexample.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import lav.cloudfirestoreexample.data.Task

/**
 * Created by Anatoliy Lukyanov on 09/03/2019.
 *
 */
class TaskDiffUtilCallback(
    private val oldList: List<Task>,
    private val newList: List<Task>
): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }
}