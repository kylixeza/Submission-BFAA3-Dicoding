package com.kylix.demosubmissionbfaa.ui.following

import androidx.lifecycle.ViewModel
import com.kylix.demosubmissionbfaa.data.remote.ApiService
import com.kylix.demosubmissionbfaa.data.remote.RetrofitService
import com.kylix.demosubmissionbfaa.model.User
import com.kylix.demosubmissionbfaa.util.ViewStateCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {

    private val retrofit = RetrofitService.create()

    fun getUserFollowing(username: String, viewCallback: ViewStateCallback<List<User>>) {
        viewCallback.onLoading()
        retrofit.getUserFollowing(username).enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val list = response.body()
                if (list.isNullOrEmpty())
                    viewCallback.onFailed(null)
                else
                    viewCallback.onSuccess(list)
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                viewCallback.onFailed(t.message)
            }

        })
    }

}