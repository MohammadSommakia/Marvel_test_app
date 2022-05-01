package com.mylivn.test


import android.app.Application
import android.content.Context
import com.mylivn.test.di.modules.ContextModule
import com.mylivn.test.di.modules.WorkManagerModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import com.mylivn.test.di.CoreComponent
import com.mylivn.test.di.DaggerAppComponent
import com.mylivn.test.di.DaggerCoreComponent

import javax.inject.Inject


/**
 * Base class for maintaining global application state.
 *
 * @see SplitCompatApplication
 */
class MarvelApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>


    lateinit var coreComponent: CoreComponent



    companion object {

        /**
         * Obtain core dagger component.
         *
         * @param context The application context
         */
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as? MarvelApp)?.coreComponent
    }

    override fun onCreate() {
        super.onCreate()
        initCoreDependencyInjection()
        initAppDependencyInjection()
    }

    /**
     * Initialize app dependency injection component.
     */
    private fun initAppDependencyInjection() {
        DaggerAppComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
            .inject(this)
    }

    /**
     * Initialize core dependency injection component.
     */
    /**
     * Initialize core dependency injection component.
     */
    private fun initCoreDependencyInjection() {
        coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(this))
            .workManagerModule(WorkManagerModule(this))
            .build()
    }


    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
