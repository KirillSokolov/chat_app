package com.test.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.chatapp.core.ui.databinding.ItemChatBinding
import com.test.domain.models.chat.Chat

class ChatAppAdapter (
    private val onItemClick: (Chat) -> Unit
) : RecyclerView.Adapter<ChatAppAdapter.Holder>() {

    var items: List<Chat> = emptyList()
        set(newList) {
            val diffUtilCallback = DiffCallback(old = field, new = newList)
            val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallback)
            field = newList
            diffUtilResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            binding = ItemChatBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(chat = items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Holder(private val binding: ItemChatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chat: Chat) {
            binding.tvName.text = chat.name
            binding.tvMembersCount.text = chat.membersCount
            binding.root.setOnClickListener { onItemClick.invoke(chat) }
        }
    }

    inner class DiffCallback(
        private val old: List<Chat>,
        private val new: List<Chat>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return old.size
        }

        override fun getNewListSize(): Int {
            return new.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition].name == new[newItemPosition].name
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition] == new[newItemPosition]
        }
    }
}