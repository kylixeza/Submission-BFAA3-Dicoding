package com.kylix.submissionbfaa3.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.kylix.submissionbfaa3.data.local.UserDao
import com.kylix.submissionbfaa3.data.local.UserDatabase

class ContentProvider : ContentProvider() {
    private lateinit var userDao: UserDao

    companion object{
        private const val AUTHORITY = "com.kylix.submissionbfaa3"
        private const val TABLE_NAME = "user_table"
        private const val USERLIST = 1
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(
                AUTHORITY,
                TABLE_NAME,
                USERLIST
            )
        }
    }

    override fun onCreate(): Boolean {
        userDao = UserDatabase.getDatabase(context as Context).userDao()
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            USERLIST -> userDao.getUserListProvider()
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        return null
    }


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Implement this to handle requests to insert a new row.")
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}
