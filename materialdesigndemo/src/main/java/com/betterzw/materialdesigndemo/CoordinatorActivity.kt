package com.betterzw.materialdesigndemo

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

/**
 * Created by zhengwu on 20-6-9.
 */
class CoordinatorActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinator_layout)



        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)

        viewPager.adapter = InnerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    class InnerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
        var curFragment: Fragment? = null

        var fragments: ArrayList<CustomFragment> = ArrayList()

        init {
            fragments.add(CustomFragment())
            fragments.add(CustomFragment())
        }

        override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
            curFragment = `object` as Fragment
            super.setPrimaryItem(container, position, `object`)
        }

        override fun getCount(): Int {
            return fragments.size;
        }

        override fun getItem(position: Int): Fragment {
            return fragments.get(position)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return "11"
                1 -> return "22"
            }
            return ""
        }

    }
}