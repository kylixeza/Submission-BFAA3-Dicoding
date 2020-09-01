package com.kylix.consumerapp.contract

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    private const val AUTHORITY = "com.kylix.submissionbfaa3"
    private const val SCHEME = "content"

    class UserColumns : BaseColumns {
        companion object {
            private const val TABLE_NAME = "user_table"
            const val ID = "id"
            const val LOGIN = "login"
            const val AVATAR_URL = "avatar_url"
            const val NAME = "name"
            const val LOCATION = "location"
            const val TYPE = "type"
            const val PUBLIC_REPOS = "public_repos"
            const val FOLLOWERS = "followers"
            const val FOLLOWING = "following"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}