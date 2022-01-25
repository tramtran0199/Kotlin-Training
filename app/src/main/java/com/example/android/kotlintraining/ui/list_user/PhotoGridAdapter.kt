package com.example.android.kotlintraining.ui.list_user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.kotlintraining.databinding.GridViewItemBinding
import com.example.android.kotlintraining.models.UserModel

class PhotoGridAdapter( private val onClickListener: OnClickListener) :
        ListAdapter<UserModel,
                PhotoGridAdapter.MarsPropertyViewHolder>(DiffCallback) {

    class MarsPropertyViewHolder(private var binding: GridViewItemBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(usersProperty: UserModel) {
            binding.property = usersProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MarsPropertyViewHolder {
        return MarsPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        val usersProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(usersProperty)
        }
        holder.bind(usersProperty)
    }

    class OnClickListener(val clickListener: (usersProperty:UserModel) -> Unit) {
        fun onClick(usersProperty:UserModel) = clickListener(usersProperty)
    }
}

