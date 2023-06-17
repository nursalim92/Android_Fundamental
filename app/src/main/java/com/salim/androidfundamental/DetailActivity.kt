package com.salim.androidfundamental

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider

import com.bumptech.glide.Glide
import com.salim.androidfundamental.adapter.PagerAdapter
import com.salim.androidfundamental.databinding.ActivityDetailBinding

import com.salim.androidfundamental.model.DetailViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel : DetailViewModel



    companion object {
    const val USERNAME = "user"
        const val EXTRA_ID = "id"
        const val EXTRA_URL = "avatar_url"


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail"

        val user = intent.getStringExtra(USERNAME)
        val avatar_url = intent.getStringExtra(EXTRA_URL)
        val id = intent.getIntExtra(EXTRA_ID, 0)

        val bundle = Bundle()
        bundle.putString(USERNAME, user)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)



        if (user != null) {
            viewModel.setDetailUsers(user)
        }
        viewModel.getUsers().observe(this) {

            if (it != null) {
                binding.apply {
                    loading(true)
                    Glide.with(this@DetailActivity)
                        .load(it.avatar_url)
                        .centerCrop()
                        .into(imageUser)

                    namaUser.text = it.name
                    emailUser.text = it.login
                    followers.text = it.followers.toString()
                    following.text = it.following.toString()
loading(false)


                }
            }
        }

    var bFavorite = false
        CoroutineScope(Dispatchers.IO).launch {
      val cekId = viewModel.cekFavorite(id)
            withContext(Dispatchers.Main){
                if (cekId != null) {
                    if (cekId > 0 ){
                        binding.addFavorite.isChecked = true
                        bFavorite = true
                    }else{
                        binding.addFavorite.isChecked = false
                        bFavorite = false
                    }
                }
            }

        }

binding.addFavorite.setOnClickListener{
    bFavorite = ! bFavorite
    if (bFavorite){
        viewModel.addFavorite(id, user, avatar_url)
            bFavorite = true
    }else{
        viewModel.hapusFavorite(id)
        bFavorite = false
    }
    binding.addFavorite.isChecked = bFavorite

}

        val pagerAdapter = PagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = pagerAdapter
            tabsLayout.setupWithViewPager(viewPager)
        }

    }

    private fun loading (stats: Boolean){
        if(stats){
            binding.progress.visibility = View.VISIBLE
        }else{
            binding.progress.visibility = View.GONE
        }
    }
}