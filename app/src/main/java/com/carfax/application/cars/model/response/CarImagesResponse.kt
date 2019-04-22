package com.carfax.application.cars.model.response

data class CarImagesResponse(
    val baseUrl: String,
    val firstPhoto: CarImagesFirstPhotoResponse
)