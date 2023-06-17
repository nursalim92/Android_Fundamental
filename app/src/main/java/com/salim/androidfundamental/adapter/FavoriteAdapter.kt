package com.salim.androidfundamental.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.salim.androidfundamental.data.Favorite
import com.salim.androidfundamental.databinding.ItemUserBinding

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private val list = ArrayList<Favorite>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    fun setList(user: ArrayList<Favorite>){
        list.clear()
        list.addAll(user)
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder (val binding : ItemUserBinding):RecyclerView.ViewHolder(binding.root){
        fun bin (user: Favorite){
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder((view))

    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bin(list[position])

    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Favorite)
    }
}