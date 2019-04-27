package com.carfax.application.cars.model.response

import com.squareup.moshi.Json

data class CarImagesResponse(
    @Json(name = "baseUrl") val baseUrl: String,
    @Json(name = "firstPhoto") val firstPhoto: CarImagesFirstPhotoResponse
)