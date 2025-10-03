package com.qurio.di.modules

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

}
