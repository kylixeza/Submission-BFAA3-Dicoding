package com.kylix.demosubmissionbfaa.ui.following

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.kylix.demosubmissionbfaa.R
import com.kylix.demosubmissionbfaa.data.DataDummy
import com.kylix.demosubmissionbfaa.databinding.FollowerFragmentBinding
import com.kylix.demosubmissionbfaa.ui.adapter.UserAdapter

class FollowingFragment : Fragment() {

    private val followingBinding: FollowerFragmentBinding by viewBinding()
    //private lateinit var viewModel: FollowingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.following_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(FollowingViewModel::class.java)

        val userAdapter = UserAdapter()
        context?.let { DataDummy.listOfUser(it) }?.let { userAdapter.setAllData(it) }

        followingBinding.rvListUserFollower.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

}