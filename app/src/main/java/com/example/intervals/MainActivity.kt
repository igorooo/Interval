package com.example.intervals

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.example.intervals.CreateIntervalFragment.OnCreateIntervalInteractionListener
import com.google.android.material.tabs.TabLayout

class MainActivity : FragmentActivity(),
                     OnCreateIntervalInteractionListener {

    private val REQUEST_CODE = 1
    private val RETURN_INTENT = "RETURN"

    private lateinit var mViewPager: ViewPager
    private lateinit var mTabLayout: TabLayout
    private lateinit var mSectionPageAdapter: SectionPageAdapter
    private lateinit var mCreateIntervalFragment: CreateIntervalFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewPager = findViewById(R.id.ViewPager_pager);
        mTabLayout = findViewById(R.id.TabLayout_tabs);
        mSectionPageAdapter = SectionPageAdapter(supportFragmentManager)

        setupViewPager(mViewPager, mSectionPageAdapter, mTabLayout)
    }

    fun setupViewPager(viewPager: ViewPager, sectionPageAdapter: SectionPageAdapter, tabLayout: TabLayout) {

        mCreateIntervalFragment = CreateIntervalFragment()
        var startIntervalFragment = StartIntervalFragment()

        sectionPageAdapter.addFragment(mCreateIntervalFragment, "Interval")
        sectionPageAdapter.addFragment(startIntervalFragment, "Training")

        tabLayout.setupWithViewPager(viewPager)
        viewPager.adapter = sectionPageAdapter
    }

    override fun onCreateIntervalInteractionListener() {
        val intent = Intent(this, AddExerciseActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            mCreateIntervalFragment.captureExerciseFromMainActivity(data!!.getParcelableExtra(RETURN_INTENT))
        }
    }

}
