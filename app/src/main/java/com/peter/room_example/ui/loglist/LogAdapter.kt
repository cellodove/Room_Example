package com.peter.room_example.ui.loglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
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
        fun onLogItemClick(log: String)
        fun onLogItemLongClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.apply {
            setListener(listener,getItem(position))
            onBind(getItem(position))
        }
    }
}

class ItemHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(log: Log){
        binding.log.text = log.msg
        binding.date.text = log.timestamp
    }

    fun setListener(listener: LogAdapter.LogListListener,log: Log) {
        binding.container.apply {
            setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onLogItemClick(log.msg)
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