package com.example.lembretedecompras

import android.app.Application
import com.facebook.stetho.Stetho

class LembrenteComprasAplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}