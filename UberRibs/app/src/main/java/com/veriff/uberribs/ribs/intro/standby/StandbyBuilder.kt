package com.veriff.uberribs.ribs.intro.standby

import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import com.veriff.uberribs.di.RibDependencies
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link StandbyScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class StandbyBuilder(dependency: ParentComponent) :
    ViewBuilder<StandbyView, StandbyRouter, StandbyBuilder.ParentComponent>(dependency) {

    /**
     * Builds a new [StandbyRouter].
     *
     * @param parentViewGroup parent view group that this router's view will be added to.
     * @return a new [StandbyRouter].
     */
    fun build(parentViewGroup: ViewGroup): StandbyRouter {
        val view = createView(parentViewGroup)
        val interactor = StandbyInteractor()
        val component = DaggerStandbyBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.standbyRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): StandbyView {
        return StandbyView(parentViewGroup.context)
    }

    interface ParentComponent : RibDependencies {
        fun standbyListener(): Listener
    }

    @dagger.Module
    abstract class Module {

        @StandbyScope
        @Binds
        internal abstract fun presenter(view: StandbyView): StandbyInteractor.StandbyPresenter

        @dagger.Module
        companion object {

            @StandbyScope
            @Provides
            @JvmStatic
            internal fun router(
                component: Component,
                view: StandbyView,
                interactor: StandbyInteractor
            ): StandbyRouter {
                return StandbyRouter(view, interactor, component)
            }
        }
    }

    @StandbyScope
    @dagger.Component(
        modules = [Module::class],
        dependencies = [ParentComponent::class]
    )
    interface Component : InteractorBaseComponent<StandbyInteractor>, BuilderComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: StandbyInteractor): Builder

            @BindsInstance
            fun view(view: StandbyView): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun standbyRouter(): StandbyRouter
    }

    interface Listener {
        fun onReadyForVerification()
    }

    @Scope
    @Retention(CLASS)
    internal annotation class StandbyScope

    @Qualifier
    @Retention(CLASS)
    internal annotation class StandbyInternal
}
