package com.carfax.application.cars.model

data class Dealer (
    val carfaxId: String = "",
    val address: String = "",
    val city: String = "",
    val latitude: Float = 0.0F,
    val longitude: Float = 0.0F,
    val name: String = "",
    val phone: String = "",
    val state: String = "",
    val zip: String = ""
)