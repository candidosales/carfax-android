package com.carfax.application.cars.presentation

data class CarView(
    val photo: String,
    val price: String,
    val year: Int,
    val make: String,
    val trim: String,
    val model: String,
    val mileage: String,
    val location: String
)