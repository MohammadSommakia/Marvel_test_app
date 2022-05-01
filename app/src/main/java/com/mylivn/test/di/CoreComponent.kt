package com.mylivn.test.di

import android.content.Context
import androidx.work.WorkManager
import com.mylivn.test.di.modules.ContextModule
import com.mylivn.test.di.modules.WorkManagerModule
import dagger.Component
import javax.inject.Singleton

/**
 * Core component that all module's components depend on.
 *
 * @see Component
 */
@Singleton
@Component(
    modules = [
        ContextModule::class,
        WorkManagerModule::class
    ]
)
interface CoreComponent {

    /**
     * Provide dependency graph Context
     *
     * @return Context
     */
    fun context(): Context

    fun workManager(): WorkManager

}