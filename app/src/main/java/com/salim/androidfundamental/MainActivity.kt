package com.salim.androidfundamental


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.salim.androidfundamental.adapter.UsersAdapter
import com.salim.androidfundamental.data.User
import com.salim.androidfundamental.databinding.ActivityMainBinding
import com.salim.androidfundamental.model.MainViewModel
import com.salim.androidfundamental.setting.SettingPreferences
import com.salim.androidfundamental.setting.SettingViewModel
import com.salim.androidfundamental.setting.ViewModelFacorys

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : MainViewModel
    private lateinit var usersAdapter: UsersAdapter
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        darkMode()

        usersAdapter = UsersAdapter()
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

   binding.apply {
       itemUser.layoutManager = LinearLayoutManager(this@MainActivity)
       itemUser.adapter = usersAdapter

       btnCari.setOnClickListener {
        search()
       }



       usersAdapter.setOnItemClickCallback(object : UsersAdapter.OnItemClickCallback {
           override fun onItemClicked(data: User) {
               val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
               intentToDetail.putExtra(DetailActivity.USERNAME, data.login)
               intentToDetail.putExtra(DetailActivity.EXTRA_ID, data.id)
               intentToDetail.putExtra(DetailActivity.EXTRA_URL, data.avatar_url)
               startActivity(intentToDetail)
           }
       })



   }
        viewModel.getUser().observe(this) {
        if(it!=null){
         usersAdapter.setList(it)
         loading(false)
}
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.favorite -> {
                val toFavorite = Intent(this@MainActivity, FavoriteActivity::class.java)
                startActivity(toFavorite)
            }

            R.id.setting -> {
                val toSetting = Intent(this@MainActivity, SettingActivity::class.java)
                startActivity(toSetting)
            }


        }
        return super.onOptionsItemSelected(item)
    }



private fun loading (stats: Boolean){
    if(stats){
        binding.progress.visibility = View.VISIBLE
    }else{
        binding.progress.visibility = View.GONE
    }
}

    private fun search(){
        binding.apply {
            val query = InputText.text.toString()
            if (query.isEmpty()) return
            loading(true)
            viewModel.setUsers(query)
        }

    }

    private fun darkMode(){
        val pref = SettingPreferences.getInstance(dataStore)
        val mainViewModel = ViewModelProvider(this, ViewModelFacorys(pref)).get(
            SettingViewModel::class.java
        )

        mainViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
   }