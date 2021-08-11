package com.kylix.demosubmissionbfaa.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kylix.demosubmissionbfaa.ui.follower.FollowerFragment
import com.kylix.demosubmissionbfaa.ui.following.FollowingFragment
import com.kylix.demosubmissionbfaa.util.Constanta.TAB_TITLES

class FollowPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowerFragment()
            1 -> fragment = FollowingFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return TAB_TITLES.size
    }
}