package com.kylix.consumerapp.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kylix.consumerapp.adapter.UserAdapter
import com.kylix.consumerapp.databinding.ActivityMainBinding
import com.kylix.consumerapp.fragment.DetailFragment
import com.kylix.consumerapp.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var usersViewModel: UserViewModel
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var usersAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        usersAdapter = UserAdapter(ArrayList()) { githubUser ->
            val fragment = DetailFragment.newInstance(githubUser)
            fragment.show(supportFragmentManager, "userDetail")
        }

        mainBinding.mainRv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = usersAdapter
        }

        usersViewModel = ViewModelProvider(
            viewModelStore,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(UserViewModel::class.java)
        visible()
        usersViewModel.userLists.observe(this, Observer { users ->
            if (!users.isNullOrEmpty()) {
                gone(false)
                usersAdapter.setData(users)
            } else {
                gone(true)
            }
        })
    }

    private fun visible(){
        mainBinding.progress.visibility = View.VISIBLE
    }

    private fun gone(error: Boolean){
        if (error){
            mainBinding.apply {
                progress.visibility = View.GONE
                errLayout.visibility = View.VISIBLE
            }
        } else {
            mainBinding.progress.visibility = View.GONE
        }
    }
}
