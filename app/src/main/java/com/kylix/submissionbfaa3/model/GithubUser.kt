package com.kylix.submissionbfaa3.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "user_table")
@Parcelize
data class GithubUser(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "login")
    val login: String,
    @ColumnInfo(name = "avatar_url")
    val avatar_url: String,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "location")
    val location: String?,
    @ColumnInfo(name = "type")
    val type: String?,
    @ColumnInfo(name = "public_repos")
    val public_repos: Int,
    @ColumnInfo(name = "followers")
    val followers: Int,
    @ColumnInfo(name = "following")
    val following: Int
) : Parcelable