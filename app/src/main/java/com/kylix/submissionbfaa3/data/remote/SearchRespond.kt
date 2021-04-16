package com.kylix.submissionbfaa3.data.remote

import android.os.Parcelable
import com.kylix.submissionbfaa3.model.GithubUser
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchRespond(
    val total_count : String,
    val incomplete_results: Boolean? = null,
    val items : List<GithubUser>
): Parcelable