package com.tsamarahanifa.finalproject

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabLayoutAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    private val fragment = mutableListOf<Fragment>()
    private val title = mutableListOf<String>()

    fun addFragment(fragment: Fragment, title: String) {
        this.fragment.add(fragment)
        this.title.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return fragment[position]
    }

    override fun getCount(): Int {
        return fragment.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }

}