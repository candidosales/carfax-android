package com.carfax.application.remote

import com.carfax.application.cars.data.CarService
import com.carfax.application.cars.model.Car
import com.carfax.application.data.source.CarDataStore
import com.carfax.application.remote.mapper.CarsEntityMapper
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single


/**
 * Remote implementation for retrieving Bufferoo instances. This class implements the
 * [BufferooRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class CarRemoteImpl constructor(
    private val carService: CarService,
    private val carsEntityMapper: CarsEntityMapper
) : CarDataStore {

    /**
     * Retrieve a list of [Bufferoo] instances from the [BufferooService].
     */
    override fun getCars(): Flowable<List<Car>> {
        return carService.getCars().map(carsEntityMapper).toFlowable()
//            .map { it.team }
//            .map {
//                val entities = mutableListOf<Car>()
//                it.forEach { entities.add(carsEntityMapper) }
//                entities
//            }
    }

    override fun clear(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun save(cars: List<Car>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCached(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLastCacheTime(lastCache: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isExpired(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}