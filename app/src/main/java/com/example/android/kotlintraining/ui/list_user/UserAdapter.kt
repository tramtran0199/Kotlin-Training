package com.example.android.kotlintraining.ui.list_user

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.kotlintraining.R
import com.example.android.kotlintraining.databinding.UserItemViewBinding
import com.example.android.kotlintraining.models.UserModel

class UserAdapter(val callback: OnClickListener): RecyclerView.Adapter<UserAdapter.UserItemViewHolder>() {
    var data: List<UserModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val item = data[position]
        holder.binding.also {
            it.model = item
            it.action = callback
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val withDataBinding: UserItemViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            UserItemViewHolder.LAYOUT,
            parent, false
        )
        return UserItemViewHolder(withDataBinding)
    }

    class UserItemViewHolder(val binding: UserItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.user_item_view
        }
    }
}

class OnClickListener(val clickListener: (userModel: UserModel) -> Unit) {
    fun onClick(userModel: UserModel) = clickListener(userModel)
}