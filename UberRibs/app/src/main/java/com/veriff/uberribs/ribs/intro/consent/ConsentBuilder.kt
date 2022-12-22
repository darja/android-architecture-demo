package com.veriff.uberribs.ribs.intro.consent

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
 * Builder for the {@link ConsentScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class ConsentBuilder(dependency: ParentComponent) :
    ViewBuilder<ConsentView, ConsentRouter, ConsentBuilder.ParentComponent>(dependency) {

    /**
     * Builds a new [ConsentRouter].
     *
     * @param parentViewGroup parent view group that this router's view will be added to.
     * @return a new [ConsentRouter].
     */
    fun build(parentViewGroup: ViewGroup): ConsentRouter {
        val view = createView(parentViewGroup)
        val interactor = ConsentInteractor()
        val component = DaggerConsentBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view.also { it.applyBranding(dependency.branding()) })
            .interactor(interactor)
            .build()
        return component.consentRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): ConsentView {
        return ConsentView(parentViewGroup.context)
    }

    interface ParentComponent: RibDependencies {
        fun consentListener(): Listener
    }

    @dagger.Module
    abstract class Module {

        @ConsentScope
        @Binds
        internal abstract fun presenter(view: ConsentView): ConsentInteractor.ConsentPresenter

        @dagger.Module
        companion object {

            @ConsentScope
            @Provides
            @JvmStatic
            internal fun router(
                component: Component,
                view: ConsentView,
                interactor: ConsentInteractor
            ): ConsentRouter {
                return ConsentRouter(view, interactor, component)
            }
        }
    }

    @ConsentScope
    @dagger.Component(
        modules = arrayOf(Module::class),
        dependencies = arrayOf(ParentComponent::class)
    )
    interface Component : InteractorBaseComponent<ConsentInteractor>, BuilderComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: ConsentInteractor): Builder

            @BindsInstance
            fun view(view: ConsentView): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent: RibDependencies {
        fun consentRouter(): ConsentRouter
    }

    interface Listener {
        fun onConsentAgreed()
        fun onConsentDeclined()
    }

    @Scope
    @Retention(CLASS)
    internal annotation class ConsentScope

    @Qualifier
    @Retention(CLASS)
    internal annotation class ConsentInternal
}
