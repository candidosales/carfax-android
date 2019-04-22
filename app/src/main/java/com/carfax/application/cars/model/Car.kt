package com.carfax.application.cars.model

import java.math.BigDecimal

data class Car (
    val photo: String,
    val dealer: Dealer,
    val price: BigDecimal,
    val year: Int,
    val make: String,
    val model: String,
    val trim: String
)