package com.carfax.application.cars.model.response

import com.carfax.application.cars.model.Dealer
import java.math.BigDecimal

data class CarResponse(
    val images: CarImagesResponse,
    val year: Int,
    val make: String,
    val model: String,
    val trim: String,
    val price: BigDecimal,
    val mileage: Int,
    val dealer: Dealer
)