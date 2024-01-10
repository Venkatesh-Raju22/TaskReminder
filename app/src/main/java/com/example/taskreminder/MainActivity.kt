package com.example.taskreminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        taskAdapter = TaskAdapter(mutableListOf())

        val rvTaskItems = findViewById<RecyclerView>(R.id.rvTaskItems)
        rvTaskItems.adapter = taskAdapter
        rvTaskItems.layoutManager = LinearLayoutManager(this)

        val btnAddTask = findViewById<Button>(R.id.btnAddTask)
        btnAddTask.setOnClickListener {
            val etTaskTitle = findViewById<EditText>(R.id.etTaskTitle)
            val todoTitle = etTaskTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                val task = Task(todoTitle, true)
                taskAdapter.addTask(task)
                etTaskTitle.text.clear()
            }
        }

        val btnDeleteDoneTasks = findViewById<Button>(R.id.btnDeleteDoneTasks)
        btnDeleteDoneTasks.setOnClickListener {
            taskAdapter.deleteDoneTodos()
        }
    }
}