package com.kylix.demosubmissionbfaa.ui.following

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kylix.demosubmissionbfaa.R
import com.kylix.demosubmissionbfaa.data.Resource
import com.kylix.demosubmissionbfaa.databinding.FollowerFragmentBinding
import com.kylix.demosubmissionbfaa.model.User
import com.kylix.demosubmissionbfaa.ui.adapter.UserAdapter
import com.kylix.demosubmissionbfaa.util.Constanta
import com.kylix.demosubmissionbfaa.util.ViewStateCallback

class FollowingFragment : Fragment(), ViewStateCallback<List<User>> {

    companion object {
        private const val KEY_BUNDLE = "USERNAME"

        fun getInstance(username: String): Fragment {
            return FollowingFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_BUNDLE, username)
                }
            }
        }
    }

    private val followingBinding: FollowerFragmentBinding by viewBinding()
    private val viewModel: FollowingViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(KEY_BUNDLE)
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.following_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userAdapter = UserAdapter()
        followingBinding.rvListUserFollower.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        viewModel.getUserFollowing(username.toString()).observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Error -> onFailed(it.message)
                is Resource.Loading -> onLoading()
                is Resource.Success -> it.data?.let { it1 -> onSuccess(it1) }
            }
        })
    }

    override fun onSuccess(data: List<User>) {
        userAdapter.setAllData(data)

        followingBinding.apply {
            tvMessage.visibility = invisible
            followProgressBar.visibility = invisible
            rvListUserFollower.visibility = visible
        }
    }

    override fun onLoading() {
        followingBinding.apply {
            tvMessage.visibility = invisible
            followProgressBar.visibility = visible
            rvListUserFollower.visibility = invisible
        }
    }

    override fun onFailed(message: String?) {
        followingBinding.apply {
            if (message == null) {
                tvMessage.text = resources.getString(R.string.following_not_found, username)
                tvMessage.visibility = visible
            } else {
                tvMessage.text = message
                tvMessage.visibility = visible
            }
            followProgressBar.visibility = invisible
            rvListUserFollower.visibility = invisible
        }
    }

}