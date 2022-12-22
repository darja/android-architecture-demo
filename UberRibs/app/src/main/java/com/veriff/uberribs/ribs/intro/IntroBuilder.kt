package com.veriff.uberribs.ribs.intro

import android.view.ViewGroup
import com.uber.rib.core.Builder
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.InteractorBaseComponent
import com.veriff.uberribs.di.RibDependencies
import com.veriff.uberribs.ribs.intro.consent.ConsentBuilder
import com.veriff.uberribs.ribs.intro.progress.FullscreenProgressBuilder
import com.veriff.uberribs.ribs.intro.standby.StandbyBuilder
import dagger.Binds
import java.lang.annotation.Retention

import javax.inject.Qualifier
import javax.inject.Scope

import dagger.Provides
import dagger.BindsInstance

import java.lang.annotation.RetentionPolicy.CLASS

class IntroBuilder(dependency: ParentComponent) :
    Builder<IntroRouter, IntroBuilder.ParentComponent>(dependency) {

    /**
     * Builds a new [IntroRouter].
     *
     * @return a new [IntroRouter].
     */
    fun build(parentViewGroup: ViewGroup): IntroRouter {
        val component = DaggerIntroBuilder_Component.builder()
            .parentComponent(dependency)
            .parentView(parentViewGroup)
            .interactor(IntroInteractor())
            .build()

        return component.introRouter()
    }

    interface ParentComponent: RibDependencies {
        fun introListener(): Listener
    }

    @dagger.Module
    abstract class Module {

        @Binds
        abstract fun consentListener(interactor: IntroInteractor): ConsentBuilder.Listener

        @Binds
        abstract fun standbyListener(interactor: IntroInteractor): StandbyBuilder.Listener

        @dagger.Module
        companion object {

            @IntroScope
            @Provides
            @JvmStatic
            internal fun presenter(): EmptyPresenter {
                return EmptyPresenter()
            }

            @IntroScope
            @Provides
            @JvmStatic
            internal fun router(
                parentView: ViewGroup,
                component: Component,
                interactor: IntroInteractor
            ): IntroRouter {
                return IntroRouter(
                    parentView = parentView,
                    interactor = interactor,
                    component = component,
                    standbyBuilder = StandbyBuilder(component),
                    consentBuilder = ConsentBuilder(component),
                    progressBuilder = FullscreenProgressBuilder(component)
                )
            }
        }
    }


    @IntroScope
    @dagger.Component(modules = [Module::class], dependencies = [ParentComponent::class])
    interface Component : InteractorBaseComponent<IntroInteractor>,
        BuilderComponent,
        StandbyBuilder.ParentComponent,
        ConsentBuilder.ParentComponent,
        FullscreenProgressBuilder.ParentComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: IntroInteractor): Builder

            @BindsInstance
            fun parentView(view: ViewGroup): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }

    }

    interface BuilderComponent {
        fun introRouter(): IntroRouter
    }

    interface Listener {
        fun onStartVerification()
        fun onCancelVerification()
    }

    @Scope
    @Retention(CLASS)
    internal annotation class IntroScope


    @Qualifier
    @Retention(CLASS)
    internal annotation class IntroInternal
}
