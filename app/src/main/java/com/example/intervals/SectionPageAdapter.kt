package com.example.intervals

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter



class SectionPageAdapter(var fm : FragmentManager) : FragmentPagerAdapter(fm){

    private var mFragmentList : ArrayList<Fragment> = ArrayList()
    private var mFragmentTitleList : ArrayList<String> = ArrayList()

    fun addFragment(fragment : Fragment, title : String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList.get(position)
    }

    override fun getItemPosition(`object`: Any): Int {
        return mFragmentList.indexOf(`object`)
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList.get(position)
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }
}