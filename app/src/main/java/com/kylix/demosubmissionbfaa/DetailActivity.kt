package com.kylix.demosubmissionbfaa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kylix.demosubmissionbfaa.databinding.ActivityDetailBinding
import com.kylix.demosubmissionbfaa.model.User

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "EXTRA_USER"
    }

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
        }
    }
}