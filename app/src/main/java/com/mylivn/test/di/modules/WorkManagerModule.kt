package com.mylivn.test.di.modules

import android.app.Application
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides

@Module
class WorkManagerModule(private val application: Application) {

    @Provides
    fun provideWorkManager(): WorkManager = WorkManager.getInstance(application)

}