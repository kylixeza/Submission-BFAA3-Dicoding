package com.kylix.demosubmissionbfaa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.kylix.demosubmissionbfaa.data.DataDummy
import com.kylix.demosubmissionbfaa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val userAdapter = UserAdapter()
        userAdapter.setAllData(DataDummy.listOfUser(this))

        mainBinding.rvListUser.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }
}