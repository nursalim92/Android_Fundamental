package com.salim.androidfundamental

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import com.salim.androidfundamental.adapter.FavoriteAdapter

import com.salim.androidfundamental.data.Favorite

import com.salim.androidfundamental.databinding.ActivityFavoriteBinding
import com.salim.androidfundamental.model.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Favorite"

        favoriteAdapter = FavoriteAdapter()
        viewModel = ViewModelProvider(
            this,)[FavoriteViewModel::class.java]

        binding.apply {
            itemFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            itemFavorite.adapter = favoriteAdapter

        }

        favoriteAdapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Favorite) {
                val intentToDetail = Intent(this@FavoriteActivity, DetailActivity::class.java)
                intentToDetail.putExtra(DetailActivity.USERNAME, data.login)
                intentToDetail.putExtra(DetailActivity.EXTRA_ID, data.id)
                intentToDetail.putExtra(DetailActivity.EXTRA_URL, data.avatar_url)
                startActivity(intentToDetail)
            }
        })

        loading(true)
        viewModel.getAllFavorite()?.observe(this){
            if (it != null){
                val aList = convertData(it)
                favoriteAdapter.setList(aList)
                loading(false)
            }
        }
    }

    private fun convertData(favoriteData: List<Favorite>): ArrayList<Favorite> {
        val listFavorite = ArrayList<Favorite>()
        for ( data in favoriteData){
            val conData = Favorite(
                data.login,
                data.id,
                data.avatar_url
            )
            listFavorite.addAll(listOf(conData))
        }
            return listFavorite
    }

    private fun loading (stats: Boolean){
        if(stats){
            binding.progress.visibility = View.VISIBLE
        }else{
            binding.progress.visibility = View.GONE
        }
    }
}