package com.carfax.application.cars.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carfax.application.R
import com.carfax.application.base.common.utils.CurrencyUtils
import com.carfax.application.cars.model.Car

class CarAdapter : RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    var cars: List<CarView> = arrayListOf()

    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val carView = cars[position]

        holder.carPrice.text = carView.price
        holder.carMake.text = carView.make
        holder.carModel.text = carView.model
        holder.carTrim.text = carView.trim
        holder.carYear.text = carView.year.toString()
        holder.carMileage.text = carView.mileage.toString()
        Glide
            .with(holder.itemView.context)
            .load(carView.photo)
            .centerCrop()
            .into(holder.carPhoto)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_car, parent, false)
        return ViewHolder(itemView)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var carYear: TextView
        var carMake: TextView
        var carModel: TextView
        var carTrim: TextView
        var carPrice: TextView
        var carPhoto: ImageView
        var carMileage: TextView

        init {
            carYear = view.findViewById(R.id.carYear)
            carMake = view.findViewById(R.id.carMake)
            carModel = view.findViewById(R.id.carModel)
            carTrim = view.findViewById(R.id.carTrim)
            carPrice = view.findViewById(R.id.carPrice)
            carPhoto = view.findViewById(R.id.carPhoto)
            carMileage = view.findViewById(R.id.carMileage)
        }

    }
}