package com.kylix.submissionbfaa3.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kylix.submissionbfaa3.R
import com.kylix.submissionbfaa3.ui.adapter.UserAdapter
import com.kylix.submissionbfaa3.databinding.FollowFragmentBinding
import com.kylix.submissionbfaa3.utils.ShowStates
import com.kylix.submissionbfaa3.utils.State
import com.kylix.submissionbfaa3.utils.TypeView
import com.kylix.submissionbfaa3.viewmodels.FollowViewModel
import com.shashank.sony.fancytoastlib.FancyToast

class FollowFragment : Fragment(), ShowStates {

    companion object {
        fun newInstance(username: String, type: String) =
            FollowFragment().apply {
                arguments = Bundle().apply {
                    putString(USERNAME, username)
                    putString(TYPE, type)
                }
            }
        private const val TYPE = "type"
        private const val USERNAME = "username"
    }

    private lateinit var followBinding: FollowFragmentBinding
    private lateinit var usersAdapter: UserAdapter
    private lateinit var followViewModel: FollowViewModel
    private lateinit var username: String
    private var type: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(USERNAME).toString()
            type = it.getString(TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        followBinding = FollowFragmentBinding.inflate(layoutInflater, container, false)
        return followBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(FollowViewModel::class.java)

        usersAdapter = UserAdapter(arrayListOf()) { user, _ ->
            FancyToast.makeText(context, user, Toast.LENGTH_SHORT, FancyToast.INFO, false).show()
        }

        followBinding.recylerFollow.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = usersAdapter
        }

        when (type) {
            resources.getString(R.string.following) -> followViewModel.setFollow(username, TypeView.FOLLOWING)
            resources.getString(R.string.followers) -> followViewModel.setFollow(username, TypeView.FOLLOWER)
            else -> followError(followBinding, null)
        }
        observeFollow()
    }

    private fun observeFollow() {
        followViewModel.dataFollow.observe(viewLifecycleOwner, Observer {
            when (it.state) {
                State.SUCCESS ->
                    if (!it.data.isNullOrEmpty()) {
                        followSuccess(followBinding)
                        usersAdapter.run { setData(it.data) }
                    } else {
                            followError(followBinding, resources.getString(R.string.not_have, username, type))
                    }
                State.LOADING -> followLoading(followBinding)
                State.ERROR -> followError(followBinding, it.message)
            }
        })
    }

    override fun followLoading(followBinding: FollowFragmentBinding): Int? {
        followBinding.apply {
            errLayout.mainNotFound.visibility = gone
            progress.start()
            progress.loadingColor = R.color.colorAccent
            recylerFollow.visibility = gone
        }
        return super.followLoading(followBinding)
    }

    override fun followSuccess(followBinding: FollowFragmentBinding): Int? {
        followBinding.apply {
            errLayout.mainNotFound.visibility = gone
            progress.stop()
            recylerFollow.visibility = visible
        }
        return super.followSuccess(followBinding)
    }

    override fun followError(followBinding: FollowFragmentBinding, message: String?): Int? {
        followBinding.apply {
            errLayout.apply {
                mainNotFound.visibility = visible
                emptyText.text = message ?: resources.getString(R.string.not_found)
            }
            progress.stop()
            recylerFollow.visibility = gone
        }
        return super.followError(followBinding, message)
    }
}