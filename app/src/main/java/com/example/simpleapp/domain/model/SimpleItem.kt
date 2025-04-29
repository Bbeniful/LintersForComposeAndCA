package com.example.simpleapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
@Parcelize
data class SimpleItem(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String,
    val price: Double
): Parcelable {

}