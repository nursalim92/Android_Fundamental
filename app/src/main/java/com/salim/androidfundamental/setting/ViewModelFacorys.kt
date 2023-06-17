package com.salim.androidfundamental.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.salim.androidfundamental.model.MainViewModel

class ViewModelFacorys(private val pref: SettingPreferences) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}