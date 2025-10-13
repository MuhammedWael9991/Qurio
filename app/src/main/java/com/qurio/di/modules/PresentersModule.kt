package com.qurio.di.modules

import com.qurio.ui.screen.character.CharacterContract
import com.qurio.ui.screen.character.CharacterPresenter
import com.qurio.ui.screen.games.GamesContract
import com.qurio.ui.screen.games.GamesPresenter
import com.qurio.ui.screen.home.HomeContract
import com.qurio.ui.screen.home.HomePresenter
import com.qurio.ui.screen.lastGames.LastGamesContract
import com.qurio.ui.screen.lastGames.LastGamesPresenter
import com.qurio.ui.screen.onbording.OnboardingContract
import com.qurio.ui.screen.onbording.OnboardingPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class PresentersModule {

    @Binds
    abstract fun bindOnboardingPresenter(
        presenter: OnboardingPresenter
    ): OnboardingContract.Presenter

    @Binds
    abstract fun bindHomePresenter(
        presenter: HomePresenter
    ): HomeContract.Presenter

    @Binds
    abstract fun bindGamesPresenter(
        presenter: GamesPresenter
    ): GamesContract.Presenter

    @Binds
    abstract fun bindLastGamesPresenter(
        presenter: LastGamesPresenter
    ): LastGamesContract.Presenter

    @Binds
    abstract fun bindCharacterPresenter(
        presenter: CharacterPresenter
    ): CharacterContract.Presenter

}
