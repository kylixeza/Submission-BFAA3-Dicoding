package com.kylix.submissionbfaa3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kylix.submissionbfaa3.R
import com.kylix.submissionbfaa3.adapter.UserAdapter
import com.kylix.submissionbfaa3.databinding.HomeFragmentBinding
import com.kylix.submissionbfaa3.utils.ShowState
import com.kylix.submissionbfaa3.utils.State
import com.kylix.submissionbfaa3.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    companion object{
        const val STATE_HOME = 1
    }

    private lateinit var homeBinding: HomeFragmentBinding
    private lateinit var homeAdapter: UserAdapter
    private val homeViewModel: HomeViewModel by navGraphViewModels(R.id.my_navigation)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        homeBinding.errLayout.emptyText.text = resources.getString(R.string.search_hint)

        homeAdapter = UserAdapter(arrayListOf()) { username, iv ->
            findNavController().navigate(HomeFragmentDirections.detailsAction(username),
                FragmentNavigatorExtras(
                    iv to username
                )
            )
        }

        homeBinding.recyclerHome.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = homeAdapter
        }

        homeBinding.searchView.apply {
            queryHint = resources.getString(R.string.search_hint)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    homeViewModel.setSearch(query)
                    homeBinding.searchView.clearFocus()
                    return true
                }
                override fun onQueryTextChange(newText: String): Boolean = false
            })
        }
        observeHome()
    }

    private fun observeHome() {
        homeViewModel.searchResult.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.state) {
                    State.SUCCESS -> {
                        resource.data?.let { users ->
                            if (!users.isNullOrEmpty()) {
                                showState().success()
                                homeAdapter.setData(users)
                            } else {
                                showState().error( null, resources)
                            }
                        }
                    }
                    State.LOADING -> showState().loading()
                    State.ERROR -> showState().error(it.message, resources)
                }
            }
        })
    }

    private fun showState(): ShowState =
        ShowState(STATE_HOME, homeBinding, null, null)
}