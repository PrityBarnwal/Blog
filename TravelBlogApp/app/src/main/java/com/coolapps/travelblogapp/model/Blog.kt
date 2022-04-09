package com.coolapps.travelblogapp.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

data class Blog(
    val `data`: List<Data>
)