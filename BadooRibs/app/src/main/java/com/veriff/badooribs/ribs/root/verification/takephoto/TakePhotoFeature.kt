package com.veriff.badooribs.ribs.root.verification.takephoto

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.veriff.badooribs.domain.logic.takephoto.TakePhotoUseCase
import com.veriff.badooribs.domain.model.VerificationArtifact
import com.veriff.badooribs.domain.model.VerificationTarget
import com.veriff.badooribs.ribs.root.verification.takephoto.TakePhotoFeature.*
import com.veriff.badooribs.util.classSimpleName
import com.veriff.badooribs.util.toObservable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

internal class TakePhotoFeature(
    target: VerificationTarget.Photo,
) : ActorReducerFeature<Wish, Effect, State, Nothing>(
    initialState = State.Camera(target),
    bootstrapper = null,
    actor = ActorImpl(),
    reducer = ReducerImpl()
) {
    sealed class State {
        data class Camera(val target: VerificationTarget.Photo) : State()
        object ProcessingPhoto : State() {
            override fun toString(): String = classSimpleName
        }
        data class PhotoTaken(val artifact: VerificationArtifact.Photo) : State()
    }

    sealed class Wish {
        object TakePhoto : Wish()
        class ChangeTarget(val target: VerificationTarget.Photo) : Wish()
    }

    sealed class Effect {
        object ProcessingPhoto : Effect()
        class PhotoTaken(val artifact: VerificationArtifact.Photo) : Effect()
        class TargetChanged(val target: VerificationTarget.Photo) : Effect()
    }

    private class ActorImpl(
        private val takePhotoUseCase: TakePhotoUseCase = TakePhotoUseCase(),
    ) : Actor<State, Wish, Effect> {
        override fun invoke(state: State, wish: Wish): Observable<out Effect> {
            return when {
                wish is Wish.TakePhoto && state is State.Camera ->
                    takePhotoUseCase.execute(state.target)
                        .map<Effect> { Effect.PhotoTaken(it) }
                        .observeOn(AndroidSchedulers.mainThread())
                        .startWith(Effect.ProcessingPhoto)

                wish is Wish.ChangeTarget -> Effect.TargetChanged(wish.target).toObservable()

                else -> Observable.empty()
            }
        }
    }

    private class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State {
            return when (effect) {
                is Effect.ProcessingPhoto -> State.ProcessingPhoto
                is Effect.PhotoTaken -> State.PhotoTaken(effect.artifact)
                is Effect.TargetChanged -> State.Camera(effect.target)
            }
        }
    }
}