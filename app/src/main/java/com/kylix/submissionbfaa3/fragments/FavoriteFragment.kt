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
import com.kylix.submissionbfaa3.utils.ShowStates
import com.kylix.submissionbfaa3.viewmodels.FavoriteViewModel

class FavoriteFragment : Fragment(), ShowStates {
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
        favoriteLoading(favoriteBinding)
        favoriteViewModel.dataFavorite.observe(viewLifecycleOwner, Observer {
            it?.let { users ->
                if (!users.isNullOrEmpty()){
                    favoriteSuccess(favoriteBinding)
                    favoriteAdapter.setData(users)
                } else {
                    favoriteError(
                        favoriteBinding,
                        resources.getString(R.string.not_have, "", resources.getString(R.string.favorite))
                    )
                }
            }
        })
    }

    override fun favoriteLoading(favoriteFragmentBinding: FavoriteFragmentBinding): Int? {
        favoriteBinding.apply {
            errlayout.mainNotFound.visibility = gone()
            progress.start()
            progress.loadingColor = R.color.colorAccent
            recyclerFav.visibility = gone()
        }
        return super.favoriteLoading(favoriteFragmentBinding)
    }

    override fun favoriteSuccess(favoriteFragmentBinding: FavoriteFragmentBinding): Int? {
        favoriteBinding.apply {
            errlayout.mainNotFound.visibility = gone()
            progress.stop()
            recyclerFav.visibility = visible()
        }
        return super.favoriteSuccess(favoriteFragmentBinding)
    }

    override fun favoriteError(
        favoriteFragmentBinding: FavoriteFragmentBinding,
        message: String?
    ): Int? {
        favoriteBinding.apply {
            errlayout.apply {
                mainNotFound.visibility = visible()
                emptyText.text = message ?: resources.getString(R.string.not_found)
            }
            progress.stop()
            recyclerFav.visibility = gone()
        }
        return super.favoriteError(favoriteFragmentBinding, message)
    }
}