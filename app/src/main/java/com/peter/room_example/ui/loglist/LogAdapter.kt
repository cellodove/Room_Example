package com.peter.room_example.ui.loglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.peter.room_example.R
import com.peter.room_example.data.Log
import com.peter.room_example.databinding.ListItemBinding

class LogAdapter(private val listener:LogListListener):ListAdapter<Log,ItemHolder>(
    object : DiffUtil.ItemCallback<Log>(){
        override fun areItemsTheSame(oldItem: Log, newItem: Log): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Log, newItem: Log): Boolean {
            return oldItem.id != newItem.id
        }
    }
) {

    interface LogListListener {
        fun onLogItemClick(position: Int)
        fun onLogItemLongClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding by lazy {
            ListItemBinding.inflate(LayoutInflater.from(parent.context))
        }
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.apply {
            setListener(listener)
            onBind(getItem(position))
        }
    }


}

class ItemHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(log: Log){
        binding.id.text = log.id.toString()
        binding.log.text = log.msg
        binding.date.text = log.timestamp.toString()
    }

    fun setListener(listener: LogAdapter.LogListListener) {
        binding.container.apply {
            setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onLogItemClick(adapterPosition)
                }
            }
            setOnLongClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onLogItemLongClick(adapterPosition)
                }

                return@setOnLongClickListener true
            }
        }
    }
}