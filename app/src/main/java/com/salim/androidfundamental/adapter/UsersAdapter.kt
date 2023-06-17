package com.salim.androidfundamental.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.salim.androidfundamental.data.User
import com.salim.androidfundamental.databinding.ItemUserBinding

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    private val list = ArrayList<User>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    fun setList(user: ArrayList<User>){
        list.clear()
        list.addAll(user)
        notifyDataSetChanged()
    }

    inner class UserViewHolder (val binding : ItemUserBinding):RecyclerView.ViewHolder(binding.root){
        fun bin (user: User){
            binding.apply {

                Glide.with(itemView)
                    .load(user.avatar_url)
                    .centerCrop()
                    .into(imageUser)

                nameUser.text = user.login

                itemDescription.text = user.id.toString()
                binding.root.setOnClickListener {
                    onItemClickCallback?.onItemClicked(user)
                }
            }

            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder((view))

    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bin(list[position])

    }
    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
}