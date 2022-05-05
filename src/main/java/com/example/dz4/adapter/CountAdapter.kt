package com.example.dz4.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dz4.databinding.ItemNoteBinding
import com.example.dz4.model.User


class CountAdapter(
    private val context: Context,
    private val onLongClick: (User) -> Unit,
) : ListAdapter<User, CountAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            binding = ItemNoteBinding.inflate(layoutInflater, parent, false),
            onLongClick = onLongClick
        )
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(
        private val binding: ItemNoteBinding,
        private val onLongClick: (User) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) {
            with(binding) {
                textName.text = item.user
                textCity.text = item.city
                textCity.setOnLongClickListener {
                    onLongClick(item)
                    true
                }
                textName.setOnLongClickListener {
                    onLongClick(item)
                    true
                }
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
