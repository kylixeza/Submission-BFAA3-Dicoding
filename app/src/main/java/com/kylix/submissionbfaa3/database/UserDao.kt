package com.kylix.submissionbfaa3.database

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.kylix.submissionbfaa3.model.GithubUser

@Dao
interface UserDao {

    @Query("SELECT * from user_table ORDER BY login ASC")
    fun getUserList(): LiveData<List<GithubUser>>

    @Query("SELECT * from user_table WHERE login = :username")
    fun getUserDetail(username: String): GithubUser?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: GithubUser)

    @Delete
    suspend fun deleteUser(model: GithubUser): Int

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("SELECT * from user_table ORDER BY login ASC")
    fun getUserListProvider(): Cursor

    @Query("SELECT * from user_table ORDER BY login ASC")
    fun getWidgetList(): List<GithubUser>
}