package com.kylix.demosubmissionbfaa.data

import android.content.Context
import android.content.res.TypedArray
import com.kylix.demosubmissionbfaa.R
import com.kylix.demosubmissionbfaa.model.User

object DataDummy {

    private lateinit var listOfUsername: Array<String>
    private lateinit var listOfName: Array<String>
    private lateinit var listOfLocation: Array<String>
    private lateinit var listOfCompany: Array<String>
    private lateinit var listOfRepository: Array<String>
    private lateinit var listOfFollower: Array<String>
    private lateinit var listOfFollowing: Array<String>
    private lateinit var listOfAvatar: TypedArray

    private fun prepareData(context: Context) {
        listOfUsername = context.resources.getStringArray(R.array.username)
        listOfName = context.resources.getStringArray(R.array.name)
        listOfLocation = context.resources.getStringArray(R.array.location)
        listOfCompany = context.resources.getStringArray(R.array.company)
        listOfRepository = context.resources.getStringArray(R.array.repository)
        listOfFollower = context.resources.getStringArray(R.array.followers)
        listOfFollowing = context.resources.getStringArray(R.array.following)
        listOfAvatar = context.resources.obtainTypedArray(R.array.avatar)
    }

    fun listOfUser(context: Context): ArrayList<User> {
        val listOfUser = ArrayList<User>()
        prepareData(context)

        for (position in listOfUsername.indices) {
            val user = User(
                username = listOfUsername[position],
                name = listOfName[position],
                location = listOfLocation[position],
                company = listOfCompany[position],
                repository = listOfRepository[position],
                follower = listOfFollower[position],
                following = listOfFollowing[position],
                avatar = listOfAvatar.getResourceId(position, 0)
            )

            listOfUser.add(user)
        }

        return listOfUser
    }
}