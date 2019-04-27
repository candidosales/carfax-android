package com.carfax.application.cache.mapper

import com.carfax.application.cache.model.CachedCar
import com.carfax.application.cars.model.Car

/**
 * Map a [CachedCar] instance to and from a [Car] instance when data is moving between
 * this later and the Data layer
 */
open class CarEntityMapper :
    EntityMapper<CachedCar, Car> {

    /**
     * Map a [Bufferoo] instance to a [CachedBufferoo] instance
     */
    override fun mapToCached(type: Car): CachedCar {
        return CachedCar(
            type.id,
            type.photo,
            type.price.toDouble(),
            type.year,
            type.make,
            type.model,
            type.trim,
            type.mileage
        )
    }

    /**
     * Map a [CachedBufferoo] instance to a [Bufferoo] instance
     */
    override fun mapFromCached(type: CachedCar): Car {
        return Car(type.id, type.photo, null, type.price.toBigDecimal(), type.year, type.make, type.model, type.trim, type.mileage)
    }

}