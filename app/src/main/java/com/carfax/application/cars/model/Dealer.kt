package com.carfax.application.cars.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dealer (
    @Json(name = "carfaxId") val carfaxId: String = "",
    @Json(name = "address") val address: String = "",
    @Json(name = "city") val city: String = "",
    @Json(name = "latitude") val latitude: Float = 0.0F,
    @Json(name = "longitude") val longitude: Float = 0.0F,
    @Json(name = "name") val name: String = "",
    @Json(name = "phone")  val phone: String = "",
    @Json(name = "state") val state: String = "",
    @Json(name = "zip") val zip: String = ""
): Parcelable