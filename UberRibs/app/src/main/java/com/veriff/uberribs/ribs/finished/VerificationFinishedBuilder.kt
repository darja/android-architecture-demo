package com.veriff.uberribs.ribs.finished

import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link VerificationFinishedScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class VerificationFinishedBuilder(dependency: ParentComponent) :
    ViewBuilder<VerificationFinishedView, VerificationFinishedRouter, VerificationFinishedBuilder.ParentComponent>(
        dependency
    ) {

    /**
     * Builds a new [VerificationFinishedRouter].
     *
     * @param parentViewGroup parent view group that this router's view will be added to.
     * @return a new [VerificationFinishedRouter].
     */
    fun build(parentViewGroup: ViewGroup): VerificationFinishedRouter {
        val view = createView(parentViewGroup)
        val interactor = VerificationFinishedInteractor()
        val component = DaggerVerificationFinishedBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.verificationFinishedRouter()
    }

    override fun inflateView(
        inflater: LayoutInflater,
        parentViewGroup: ViewGroup
    ): VerificationFinishedView {
        return VerificationFinishedView(parentViewGroup.context)
    }

    interface ParentComponent {
        // TODO: Define dependencies required from your parent interactor here.
    }

    @dagger.Module
    abstract class Module {

        @VerificationFinishedScope
        @Binds
        internal abstract fun presenter(view: VerificationFinishedView): VerificationFinishedInteractor.VerificationFinishedPresenter

        @dagger.Module
        companion object {

            @VerificationFinishedScope
            @Provides
            @JvmStatic
            internal fun router(
                component: Component,
                view: VerificationFinishedView,
                interactor: VerificationFinishedInteractor
            ): VerificationFinishedRouter {
                return VerificationFinishedRouter(view, interactor, component)
            }
        }
    }

    @VerificationFinishedScope
    @dagger.Component(
        modules = [Module::class],
        dependencies = [ParentComponent::class]
    )
    interface Component : InteractorBaseComponent<VerificationFinishedInteractor>,
        BuilderComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: VerificationFinishedInteractor): Builder

            @BindsInstance
            fun view(view: VerificationFinishedView): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun verificationFinishedRouter(): VerificationFinishedRouter
    }

    @Scope
    @Retention(CLASS)
    internal annotation class VerificationFinishedScope

    @Qualifier
    @Retention(CLASS)
    internal annotation class VerificationFinishedInternal
}
