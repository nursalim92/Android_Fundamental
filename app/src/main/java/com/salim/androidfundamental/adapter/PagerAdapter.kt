package com.salim.androidfundamental.adapter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.salim.androidfundamental.R
import com.salim.androidfundamental.fragment.FollowerFragment
import com.salim.androidfundamental.fragment.FollowingFragment

class PagerAdapter (private val m : Context, fm: FragmentManager, datauser : Bundle): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT ) {

    private var fragBundle: Bundle
    init {
        fragBundle = datauser
    }



    private val tabTitles = intArrayOf(R.string.tab_1, R.string.tab_2)

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment? = null
        when(position){
            0 -> fragment = FollowerFragment()
            1 -> fragment = FollowingFragment()

        }
        fragment?.arguments = this.fragBundle
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return m.resources.getString(tabTitles[position])
    }
}