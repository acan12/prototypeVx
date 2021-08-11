package com.otto.ottosdk

import android.R
import android.content.Context
import app.beelabs.com.codebase.base.BaseApp
import app.beelabs.com.codebase.di.component.AppComponent
import app.beelabs.com.codebase.di.component.DaggerAppComponent
import uk.co.chrisjenx.calligraphy.CalligraphyConfig


class App : BaseApp() {
    companion object {
        private var context: Context? = null
        private var appComponent: AppComponent? = null

        fun setupComponent(component: AppComponent?) {
            appComponent = component
        }

        fun getAppComponent(): AppComponent? {
            return appComponent
        }

        fun getContext(): Context? {
            return context
        }

    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        setupBuilder(DaggerAppComponent.builder(), this)
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .disableCustomViewInflation()
                .build()
        )


    }
}