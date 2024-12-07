package id.haonlabs.artsie.feature.home.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Art(
    val title: String,
    val history: String,
    val materials: String,
    val dimensions: String,
    val photo: Int,
) : Parcelable
