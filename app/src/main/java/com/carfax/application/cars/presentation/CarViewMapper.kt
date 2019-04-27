package com.carfax.application.cars.presentation

import com.carfax.application.R
import com.carfax.application.base.common.utils.CurrencyUtils
import com.carfax.application.base.presentation.providers.StringProvider
import com.carfax.application.cars.model.Car
import com.carfax.application.cars.model.Dealer
import io.reactivex.functions.Function
import java.math.BigDecimal

class CarViewMapper(
    private val stringProvider: StringProvider,
    private val currencyUtils: CurrencyUtils
) : Function<List<Car>, List<CarView>> {

    @Throws(Exception::class)
    override fun apply(cars: List<Car>): List<CarView> {

        return cars.map { car ->
            CarView(
                photo = car.photo,
                mileage = mapToMileage(car.mileage),
                year = car.year,
                make = car.make,
                trim = car.trim,
                model = car.model,
                price = formatPrice(car.price),
                location = formatLocation(car.dealer!!)
            )
        }


    }

    private fun mapToMileage(mileage: Int): String {
        return stringProvider.getStringAndApplySubstitutions(
            R.string.car_mileage,
            Pair("carMileage", (mileage / 1000).toString())
        )
    }

    private fun formatPrice(price: BigDecimal): String {
        return currencyUtils.formatAmount(price)
    }

    private fun formatLocation(dealer: Dealer) : String {
        return "${dealer.city}, ${dealer.state}"
    }
}