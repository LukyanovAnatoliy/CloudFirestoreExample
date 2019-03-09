package lav.cloudfirestoreexample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import lav.cloudfirestoreexample.R
import lav.cloudfirestoreexample.ui.adapter.OnTaskClickListener
import lav.cloudfirestoreexample.ui.adapter.TaskAdapter
import lav.cloudfirestoreexample.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), OnTaskClickListener {

    private lateinit var viewModel: MainViewModel
    private val taskAdapter = TaskAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler.adapter = taskAdapter

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.taskList.observe(this, Observer {
            taskAdapter.setItems(it)
        })

        addTask.setOnClickListener {
            viewModel.addTask(taskTitle.text.toString())
            taskTitle.text?.clear()
        }
    }

    override fun onDeleteClick(taskId: String) {
        viewModel.deleteTask(taskId)
    }
}
