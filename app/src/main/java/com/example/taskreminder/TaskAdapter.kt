package com.example.taskreminder

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import kotlinx.android.synthetic.main.item_todo.view.*

class TaskAdapter(
    private val tasks: MutableList<Task>
) : RecyclerView.Adapter<TaskAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_task,
                parent,
                false
            )
        )
    }

    fun addTask(task: Task) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }

    fun deleteDoneTodos() {
        tasks.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTask = tasks[position]
        holder.itemView.apply {
            val tvTaskTitle = findViewById<TextView>(R.id.tvTaskTitle)
            tvTaskTitle.text = curTask.title
            val cbDone = findViewById<CheckBox>(R.id.cbDone)
            cbDone.isChecked = curTask.isChecked
            toggleStrikeThrough(tvTaskTitle, curTask.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTaskTitle, isChecked)
                curTask.isChecked = !curTask.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}