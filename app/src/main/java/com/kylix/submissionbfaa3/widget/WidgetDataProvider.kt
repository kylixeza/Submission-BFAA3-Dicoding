package com.kylix.submissionbfaa3.widget

import android.R
import android.content.Context
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.kylix.submissionbfaa3.database.UserDao
import com.kylix.submissionbfaa3.database.UserDatabase
import com.kylix.submissionbfaa3.model.GithubUser

class WidgetDataProvider(private val context: Context): RemoteViewsService.RemoteViewsFactory {

    private lateinit var githubUsers: List<GithubUser>
    private lateinit var userDao: UserDao

    override fun onCreate() {
        userDao = UserDatabase.getDatabase(context).userDao()
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getItemId(position: Int): Long = githubUsers[position].id.toLong()

    override fun onDataSetChanged() {
        githubUsers = userDao.getWidgetList()
    }

    override fun hasStableIds(): Boolean = true

    override fun getViewAt(position: Int): RemoteViews {
        val remoteViews = RemoteViews(context.packageName, R.layout.simple_list_item_1)
        remoteViews.setTextViewText(R.id.text1, githubUsers[position].login)
        return remoteViews
    }

    override fun getCount(): Int = githubUsers.size

    override fun getViewTypeCount(): Int = 1

    override fun onDestroy() {
    }
}