package com.kylix.demosubmissionbfaa.ui.detail

import androidx.lifecycle.ViewModel
import com.kylix.demosubmissionbfaa.data.remote.ApiService
import com.kylix.demosubmissionbfaa.data.remote.RetrofitService
import com.kylix.demosubmissionbfaa.model.User
import com.kylix.demosubmissionbfaa.util.ViewStateCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailViewModel: ViewModel() {

    private val retrofit = RetrofitService.create()

    fun getDetailUser(username: String?, viewCallback: ViewStateCallback<User?>) {

        retrofit.getDetailUser(username!!).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()
                viewCallback.onSuccess(user)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {

            }

        })
    }
}