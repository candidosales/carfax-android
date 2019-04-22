package com.carfax.application

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.carfax.application.base.presentation.BaseActivity
import com.carfax.application.cars.presentation.CarsFragment
import org.koin.android.ext.android.inject


class MainActivity : BaseActivity() {

    override val layoutId: Int
        get() = R.layout.activity_main

    private val carsFragment: CarsFragment by inject()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.app_name)

        if (savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, carsFragment)
                .commitAllowingStateLoss()
    }
}