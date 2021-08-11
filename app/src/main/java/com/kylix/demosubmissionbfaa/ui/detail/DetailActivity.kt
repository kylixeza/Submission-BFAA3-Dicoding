package com.kylix.demosubmissionbfaa.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.kylix.demosubmissionbfaa.databinding.ActivityDetailBinding
import com.kylix.demosubmissionbfaa.model.User
import com.kylix.demosubmissionbfaa.ui.adapter.FollowPagerAdapter
import com.kylix.demosubmissionbfaa.util.Constanta
import com.kylix.demosubmissionbfaa.util.Constanta.EXTRA_USER
import com.kylix.demosubmissionbfaa.util.Constanta.TAB_TITLES

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        val user = intent.getParcelableExtra<User>(EXTRA_USER)

        detailBinding.apply {
            tvDetailNumberOfRepos.text = user?.repository
            tvDetailNumberOfFollowers.text = user?.follower
            tvDetailNumberOfFollowing.text = user?.following
            tvDetailName.text = user?.name
            tvDetailCompany.text = user?.company
            tvDetailLocation.text = user?.location
        }

        Glide.with(this)
            .load(user?.avatar)
            .apply(RequestOptions.circleCropTransform())
            .into(detailBinding.ivDetailAvatar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = user?.username
            elevation = 0f
        }

        val pageAdapter = FollowPagerAdapter(this)

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
}