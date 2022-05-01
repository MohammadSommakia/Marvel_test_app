package com.mylivn.test.di

import com.mylivn.data.di.ApiModule
import com.mylivn.data.di.RepositoryModule
import com.mylivn.domain.di.scopes.AppScope
import com.mylivn.test.MarvelApp
import dagger.Component
import dagger.android.AndroidInjectionModule

/**
 * App component that application component's components depend on.
 *
 * @see Component
 */
@AppScope
@Component(
    dependencies = [
        CoreComponent::class,
    ],
    modules = [
        AppModule::class,
        ActivityModule::class,
        AndroidInjectionModule::class,
        ApiModule::class,
        RepositoryModule::class,
        MarvelViewModelModule::class,
    ]
)
interface AppComponent {

    /**
     * Inject dependencies on application.
     *
     * @param application The sample application.
     */
    fun inject(application: MarvelApp)


}
