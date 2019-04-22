package com.carfax.application.cars.data

import com.carfax.application.cars.model.response.CarResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CarsResponseRaw(
    @Json(name = "listings") val listings: List<CarResponse>
)