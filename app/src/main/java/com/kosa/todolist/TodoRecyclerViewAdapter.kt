package com.kosa.todolist

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kosa.todolist.databinding.ItemTodoBinding
import com.kosa.todolist.db.ToDoEntity
import kotlin.math.log

class TodoRecyclerViewAdapter(
    private val todoList: ArrayList<ToDoEntity>,
    private val listener: OnItemLongClickListener
) :
    RecyclerView.Adapter<TodoRecyclerViewAdapter.MyViewHolder>() {
    inner class MyViewHolder(binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        val tv_importance = binding.tvImportance
        val tv_title = binding.tvTitle

        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemTodoBinding = ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val todoData = todoList[position]

        when (todoData.importance) {
            1 -> {
                holder.tv_importance.setBackgroundResource(R.color.red)
            }
            2 -> {
                holder.tv_importance.setBackgroundResource(R.color.yello)
            }
            3 -> {
                holder.tv_importance.setBackgroundResource(R.color.green)
            }
        }

        holder.tv_importance.text = todoData.importance.toString()

        holder.tv_title.text = todoData.title
        holder.root.setOnLongClickListener {
            Log.i("test","잘되닝?")
            listener.onLongClick(position)
            false//람다의 리턴값인가봐 생략이 있어서 잘 모르겠네
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}