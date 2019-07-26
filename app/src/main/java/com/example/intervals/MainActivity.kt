package com.example.intervals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : FragmentActivity() {

    private lateinit var mViewPager: ViewPager
    private lateinit var mTabLayout: TabLayout
    private lateinit var mSectionPageAdapter: SectionPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewPager = findViewById<ViewPager>(R.id.ViewPager_pager);
        mTabLayout = findViewById<TabLayout>(R.id.TabLayout_tabs);
        mSectionPageAdapter = SectionPageAdapter(supportFragmentManager)

        setupViewPager(mViewPager, mSectionPageAdapter, mTabLayout)
    }

    fun setupViewPager(viewPager: ViewPager, sectionPageAdapter: SectionPageAdapter, tabLayout: TabLayout) {

        var createIntervalFragment = CreateIntervalFragment()
        var startIntervalFragment = StartIntervalFragment()

        sectionPageAdapter.addFragment(createIntervalFragment, "Interval")
        sectionPageAdapter.addFragment(startIntervalFragment, "Training")

        tabLayout.setupWithViewPager(viewPager)
        viewPager.adapter = sectionPageAdapter
    }
}
