package com.carfax.application.cars.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.carfax.application.R
import com.carfax.application.base.common.utils.CurrencyUtils
import com.carfax.application.cars.model.Car

class CarAdapter(private val currencyUtils: CurrencyUtils, private val showHeart: Boolean = false) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    private var cars: List<Car>? = null

    fun setDataSource(dataSource: List<Car>) {
        this.cars = dataSource
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = cars?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val carView = mapper(cars!![position])
        holder.bind(carView)
    }

    private fun mapper(car: Car) : CarView {
        return CarView(
            price = currencyUtils.formatAmount(car.price),
            year = car.year,
            make = car.make,
            trim = car.trim,
            model = car.model
        )
    }


    class CarViewHolder(view: View) : ViewHolder(view) {

        private val carYear: TextView = view.findViewById(R.id.carYear)
        private val carMake: TextView = view.findViewById(R.id.carMake)
        private val carModel: TextView = view.findViewById(R.id.carModel)
        private val carTrim: TextView = view.findViewById(R.id.carTrim)

        private val carPrice: TextView = view.findViewById(R.id.carPrice)

        fun bind(carView: CarView) {
            carYear.text = carView.year.toString()
            carMake.text = carView.make
            carModel.text = carView.model
            carTrim.text = carView.trim
            carPrice.text = carView.price

//            Glide.with(view.context).load(heroView.imageUrl).into(heroUserImage)
        }

    }
}