package com.salim.androidfundamental.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.salim.androidfundamental.DetailActivity
import com.salim.androidfundamental.R
import com.salim.androidfundamental.adapter.UsersAdapter
import com.salim.androidfundamental.data.User
import com.salim.androidfundamental.databinding.FragFollowBinding
import com.salim.androidfundamental.model.FollowerViewModel
import com.salim.androidfundamental.model.MainViewModel


class FollowerFragment : Fragment(R.layout.frag_follow) {

    private var bindings: FragFollowBinding?=null
    private val binding get() = bindings!!
    private lateinit var viewModel: FollowerViewModel
    private lateinit var adapter: UsersAdapter
    private lateinit var username : String





    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        username = args?.getString(DetailActivity.USERNAME).toString()

        bindings = FragFollowBinding.bind(view)
        adapter = UsersAdapter()


        binding.apply {
            followerUser.layoutManager = LinearLayoutManager(activity)
            followerUser.adapter = adapter

        }


        loading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[FollowerViewModel::class.java]
        viewModel.setDataFollower(username)
        viewModel.getDataFollower().observe(viewLifecycleOwner) {
            if (it != null){
                adapter.setList(it)
                loading(false)
            }
        }

        adapter.setOnItemClickCallback(object : UsersAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                val intentToDetail = Intent(context, DetailActivity::class.java)
                intentToDetail.putExtra(DetailActivity.USERNAME, data.login)
                intentToDetail.putExtra(DetailActivity.EXTRA_ID, data.id)
                intentToDetail.putExtra(DetailActivity.EXTRA_URL, data.avatar_url)
                startActivity(intentToDetail)
            }
        })


    }

    override fun onDestroy() {
        super.onDestroy()
        bindings = null
    }

    private fun loading (stats: Boolean){
        if(stats){
            binding.progress.visibility = View.VISIBLE
        }else{
            binding.progress.visibility = View.GONE
        }
    }
}
