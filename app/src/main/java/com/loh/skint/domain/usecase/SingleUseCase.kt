package com.loh.skint.domain.usecase

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T, P> constructor(private val compositeDisposable: CompositeDisposable) : UseCase<Single<T>, P> {

    fun execute(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit, params: P) {
        addDisposable(getInvoke(params).subscribe({ onSuccess(it) }, { onError(it) }))
    }

    override fun getInvoke(params: P): Single<T> {
        return build(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    override fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun dispose() {
        compositeDisposable.dispose()
    }
}