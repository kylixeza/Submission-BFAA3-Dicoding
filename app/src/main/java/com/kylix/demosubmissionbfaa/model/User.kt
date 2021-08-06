package com.kylix.demosubmissionbfaa.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    val username: String,
    val name: String,
    val location: String,
    val company: String,
    val repository: String,
    val follower: String,
    val following: String,
    val avatar: Int
): Parcelable
