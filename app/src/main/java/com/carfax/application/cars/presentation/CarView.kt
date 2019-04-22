package com.carfax.application.cars.presentation

data class CarView(
    val price: String,
    val year: Int,
    val make: String,
    val trim: String,
    val model: String
)