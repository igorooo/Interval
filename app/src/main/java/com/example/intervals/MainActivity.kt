package com.example.intervals

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.example.intervals.CreateIntervalFragment.OnCreateIntervalInteractionListener
import com.google.android.material.tabs.TabLayout

class MainActivity : FragmentActivity(),
                     OnCreateIntervalInteractionListener {

    companion object{
        val STORAGE_POS_KEY = "ExerciseList"
    }

    private val REQUEST_CODE = 1
    private val RETURN_INTENT = "RETURN"
    private val TAG = "MainActivity"

    private lateinit var mStorage: Storage
    private lateinit var mViewPager: ViewPager
    private lateinit var mTabLayout: TabLayout
    private lateinit var mSectionPageAdapter: SectionPageAdapter
    private lateinit var mCreateIntervalFragment: CreateIntervalFragment
    private lateinit var mStartIntervalFragment: StartIntervalFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewPager = findViewById(R.id.ViewPager_pager)
        mTabLayout = findViewById(R.id.TabLayout_tabs)
        mSectionPageAdapter = SectionPageAdapter(supportFragmentManager)
        mStorage = Storage(applicationContext)

        setupViewPager(mViewPager, mSectionPageAdapter, mTabLayout)
    }

    fun setupViewPager(viewPager: ViewPager, sectionPageAdapter: SectionPageAdapter, tabLayout: TabLayout) {
        Log.d(TAG, "setupViewPager")

        mCreateIntervalFragment = CreateIntervalFragment(mStorage)
        mStartIntervalFragment = StartIntervalFragment()

        sectionPageAdapter.addFragment(mCreateIntervalFragment, "Interval")
        sectionPageAdapter.addFragment(mStartIntervalFragment, "Training")

        tabLayout.setupWithViewPager(viewPager)
        viewPager.adapter = sectionPageAdapter
    }

    override fun onCreateIntervalInteractionListener() {
        Log.d(TAG, "onCreateIntervalInteractionListener")
        val intent = Intent(this, AddExerciseActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(TAG, "onActivityResult")

        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            mStorage.append(data!!.getParcelableExtra(RETURN_INTENT), STORAGE_POS_KEY)
            mCreateIntervalFragment.notifyNewExerciseAdded()
        }
    }

}
