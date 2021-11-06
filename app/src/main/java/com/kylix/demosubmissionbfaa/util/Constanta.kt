package com.kylix.demosubmissionbfaa.util

import androidx.annotation.StringRes
import com.kylix.demosubmissionbfaa.R

object Constanta {
    const val TIME_SPLASH = 2000L

    const val EXTRA_USER = "EXTRA_USER"

    @StringRes
    val TAB_TITLES = intArrayOf(
        R.string.followers,
        R.string.following
    )

    const val GITHUB_TOKEN = "055044b02ef50083d69daf0f7b96531f2928e047"

    const val BASE_URL = "https://api.github.com"
}