package com.kylix.consumerapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kylix.consumerapp.databinding.FragmentDetailBinding
import com.kylix.consumerapp.model.GithubUser

class DetailFragment : BottomSheetDialogFragment() {

    private lateinit var githubUser: GithubUser
    private lateinit var binding: FragmentDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            githubUser = it.getParcelable(USER)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        binding.data = githubUser
        return binding.root
    }

    companion object {
        private const val USER = "USER"

        @JvmStatic
        fun newInstance(githubUser: GithubUser) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(USER, githubUser)
                }
            }
    }
}