package id.haonlabs.artsie.feature.home.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Art(
    val name: String,
    val desc: String,
    val artist: String,
    val photo: Int,
) : Parcelable
