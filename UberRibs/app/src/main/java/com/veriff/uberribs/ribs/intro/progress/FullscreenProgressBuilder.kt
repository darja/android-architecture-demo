package com.veriff.uberribs.ribs.intro.progress

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
 * Builder for the {@link FullscreenProgressScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class FullscreenProgressBuilder(dependency: ParentComponent) :
    ViewBuilder<FullscreenProgressView, FullscreenProgressRouter, FullscreenProgressBuilder.ParentComponent>(
        dependency
    ) {

    /**
     * Builds a new [FullscreenProgressRouter].
     *
     * @param parentViewGroup parent view group that this router's view will be added to.
     * @return a new [FullscreenProgressRouter].
     */
    fun build(parentViewGroup: ViewGroup): FullscreenProgressRouter {
        val view = createView(parentViewGroup)
        val interactor = FullscreenProgressInteractor()
        val component = DaggerFullscreenProgressBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.fullscreenprogressRouter()
    }

    override fun inflateView(
        inflater: LayoutInflater,
        parentViewGroup: ViewGroup
    ): FullscreenProgressView {
        return FullscreenProgressView(parentViewGroup.context)
    }

    interface ParentComponent : RibDependencies

    @dagger.Module
    abstract class Module {

        @FullscreenProgressScope
        @Binds
        internal abstract fun presenter(view: FullscreenProgressView): FullscreenProgressInteractor.FullscreenProgressPresenter

        @dagger.Module
        companion object {

            @FullscreenProgressScope
            @Provides
            @JvmStatic
            internal fun router(
                component: Component,
                view: FullscreenProgressView,
                interactor: FullscreenProgressInteractor
            ): FullscreenProgressRouter {
                return FullscreenProgressRouter(view, interactor, component)
            }
        }

        // TODO: Create provider methods for dependencies created by this Rib. These should be static.
    }

    @FullscreenProgressScope
    @dagger.Component(
        modules = arrayOf(Module::class),
        dependencies = arrayOf(ParentComponent::class)
    )
    interface Component : InteractorBaseComponent<FullscreenProgressInteractor>, BuilderComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: FullscreenProgressInteractor): Builder

            @BindsInstance
            fun view(view: FullscreenProgressView): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun fullscreenprogressRouter(): FullscreenProgressRouter
    }

    @Scope
    @Retention(CLASS)
    internal annotation class FullscreenProgressScope

    @Qualifier
    @Retention(CLASS)
    internal annotation class FullscreenProgressInternal
}
