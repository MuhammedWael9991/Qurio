package com.qurio.di

import android.app.Application
import com.qurio.QurioApp
import com.qurio.di.modules.AppModule
import com.qurio.di.modules.DatabaseModule
import com.qurio.di.modules.NetworkModule
import com.qurio.di.modules.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent {
    fun inject(app: QurioApp)

//     fragments/activities that need injection:
    //fun inject(onboardingFragment: onboarding.OnboardingFragment)
//     fun inject(otherFragment: ...)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }
}
