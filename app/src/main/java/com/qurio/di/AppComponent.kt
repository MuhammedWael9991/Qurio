package com.qurio.di

import android.app.Application
import com.qurio.QurioApp
import com.qurio.di.modules.AppModule
import com.qurio.di.modules.DatabaseModule
import com.qurio.di.modules.NetworkModule
import com.qurio.di.modules.PresentersModule
import com.qurio.di.modules.RepositoryModule
import com.qurio.ui.screen.character.CharacterFragment
import com.qurio.ui.screen.games.GamesFragment
import com.qurio.ui.screen.home.HomeFragment
import com.qurio.ui.screen.lastGames.LastGamesFragment
import com.qurio.ui.screen.onbording.OnboardingFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        DatabaseModule::class,
        PresentersModule::class
    ]
)
interface AppComponent {
    fun inject(app: QurioApp)

    fun inject(onboardingFragment: OnboardingFragment)
    fun inject(homeFragment: HomeFragment)
    fun inject(gamesFragment: GamesFragment)
    fun inject(lastGamesFragment: LastGamesFragment)
    fun inject(characterFragment: CharacterFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }
}
