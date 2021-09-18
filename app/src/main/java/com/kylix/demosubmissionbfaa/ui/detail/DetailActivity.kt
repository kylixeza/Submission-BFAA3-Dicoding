package com.kylix.demosubmissionbfaa.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.kylix.demosubmissionbfaa.data.Resource
import com.kylix.demosubmissionbfaa.data.remote.RetrofitService
import com.kylix.demosubmissionbfaa.databinding.ActivityDetailBinding
import com.kylix.demosubmissionbfaa.model.User
import com.kylix.demosubmissionbfaa.ui.adapter.FollowPagerAdapter
import com.kylix.demosubmissionbfaa.ui.follower.FollowerFragment
import com.kylix.demosubmissionbfaa.ui.following.FollowingFragment
import com.kylix.demosubmissionbfaa.util.Constanta
import com.kylix.demosubmissionbfaa.util.Constanta.EXTRA_USER
import com.kylix.demosubmissionbfaa.util.Constanta.TAB_TITLES
import com.kylix.demosubmissionbfaa.util.ViewStateCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity(), ViewStateCallback<User?> {

    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            elevation = 0f
        }

        val username = intent.getStringExtra(EXTRA_USER)

        viewModel.getDetailUser(username).observe(this, {
            when (it) {
                is Resource.Error -> onFailed(it.message)
                is Resource.Loading -> onLoading()
                is Resource.Success -> onSuccess(it.data)
            }
        })

        val pageAdapter = FollowPagerAdapter(this, username.toString())

        detailBinding.apply {
            viewPager.adapter = pageAdapter
            TabLayoutMediator(tabs, viewPager) { tabs, position ->
                tabs.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onSuccess(data: User?) {
        detailBinding.apply {
            tvDetailNumberOfRepos.text = data?.repository.toString()
            tvDetailNumberOfFollowers.text = data?.follower.toString()
            tvDetailNumberOfFollowing.text = data?.following.toString()
            tvDetailName.text = data?.name
            tvDetailCompany.text = data?.company
            tvDetailLocation.text = data?.location

            Glide.with(this@DetailActivity)
                .load(data?.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(ivDetailAvatar)

            supportActionBar?.title = data?.username
        }
    }

    override fun onLoading() {

    }

    override fun onFailed(message: String?) {

    }
}