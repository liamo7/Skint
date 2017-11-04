package com.loh.skint.domain.usecase

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T, P> constructor(private val compositeDisposable: CompositeDisposable) : UseCase<Single<T>, P> {

    fun execute(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit, params: P) {
        val single = build(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        addDisposable(single.subscribe({ onSuccess(it) }, { onError(it) }))
    }

    override fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun dispose() {
        compositeDisposable.dispose()
    }
}