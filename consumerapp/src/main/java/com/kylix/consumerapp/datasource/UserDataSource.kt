package com.kylix.consumerapp.datasource

import android.content.ContentResolver
import com.kylix.consumerapp.contract.DatabaseContract
import com.kylix.consumerapp.contract.DatabaseContract.UserColumns.Companion.CONTENT_URI
import com.kylix.consumerapp.model.GithubUser

class UserDataSource(private val contentResolver: ContentResolver) {

    fun getUsers(): List<GithubUser> {
        val result: MutableList<GithubUser> = mutableListOf()

        val cursor = contentResolver.query(
            CONTENT_URI,
            null,
            null,
            null,
            null
        )

        cursor?.apply {
            while (moveToNext()) {
                result.add(
                    GithubUser(
                        getInt(getColumnIndexOrThrow(DatabaseContract.UserColumns.ID)),
                        getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.LOGIN)),
                        getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.AVATAR_URL)),
                        getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.NAME)),
                        getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.LOCATION)),
                        getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.TYPE)),
                        getInt(getColumnIndexOrThrow(DatabaseContract.UserColumns.PUBLIC_REPOS)),
                        getInt(getColumnIndexOrThrow(DatabaseContract.UserColumns.FOLLOWERS)),
                        getInt(getColumnIndexOrThrow(DatabaseContract.UserColumns.FOLLOWING))
                    )
                )
            }
            close()
        }
        return result.toList()
    }
}