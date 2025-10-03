package com.qurio

import android.app.Application
import com.qurio.di.AppComponent
import com.qurio.di.DaggerAppComponent

class QurioApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

        appComponent.inject(this)
    }
}
