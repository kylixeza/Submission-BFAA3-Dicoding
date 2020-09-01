package com.kylix.submissionbfaa3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kylix.submissionbfaa3.R
import com.kylix.submissionbfaa3.adapter.UserAdapter
import com.kylix.submissionbfaa3.databinding.FavoriteFragmentBinding
import com.kylix.submissionbfaa3.utils.ShowState
import com.kylix.submissionbfaa3.viewmodels.FavoriteViewModel

class FavoriteFragment : Fragment() {
    companion object{
        const val STATE_FAVORITE = 3
    }
    private lateinit var favoriteBinding: FavoriteFragmentBinding
    private lateinit var favoriteAdapter: UserAdapter
    private val favoriteViewModel: FavoriteViewModel by navGraphViewModels(R.id.my_navigation)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = context?.resources?.getString(R.string.favorite)
        favoriteBinding = FavoriteFragmentBinding.inflate(layoutInflater, container, false)
        return favoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteAdapter = UserAdapter(arrayListOf()) { username, iv ->
            findNavController().navigate(
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailsDestination(username),
                FragmentNavigatorExtras(iv to username)
            )
        }

        favoriteBinding.recyclerFav.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = favoriteAdapter
        }

        observeFavorite()
    }

    private fun observeFavorite() {
        showState().loading()
        favoriteViewModel.dataFavorite.observe(viewLifecycleOwner, Observer {
            it?.let { users ->
                if (!users.isNullOrEmpty()){
                    showState().success()
                    favoriteAdapter.setData(users)
                } else {
                    showState().error(
                        resources.getString(R.string.not_have,"",resources.getString(R.string.favorite)),
                        resources)
                }
            }
        })
    }

    private fun showState(): ShowState =
        ShowState(STATE_FAVORITE, null, null, favoriteBinding)
}