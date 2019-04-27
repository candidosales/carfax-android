package com.carfax.application.cars.data

import com.carfax.application.base.BaseMapper
import com.carfax.application.base.extensions.empty
import com.carfax.application.cars.model.Car
import com.carfax.application.cars.model.Dealer
import java.math.BigDecimal

class CarsMapper : BaseMapper<CarsResponseRaw, List<Car>>() {

    override fun checkParams(raw: CarsResponseRaw, missingFields: MutableList<String>) {
        if (raw.listings == null) {
            missingFields.add("listings")
        }
    }

    override fun copyValues(raw: CarsResponseRaw): List<Car> {
        return if (raw.listings.isEmpty()) {
            emptyList()
        } else {
            raw.listings.map { car ->
                Car(
                    photo = car.images?.firstPhoto?.large ?: String.empty(),
                    price = car.price.toBigDecimal() ?: BigDecimal.ZERO,
                    year = car.year ?: 0,
                    make = car.make ?: String.empty(),
                    dealer = car.dealer ?: Dealer(),
                    model = car.model ?: String.empty(),
                    trim = car.model ?: String.empty(),
                    mileage = car.mileage ?: 0
                )
            }
        }
    }

}