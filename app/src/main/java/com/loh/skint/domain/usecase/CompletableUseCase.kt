package com.loh.skint.domain.usecase

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<P> constructor(private val compositeDisposable: CompositeDisposable)
    : UseCase<Completable, P> {

    fun execute(singleObserver: DisposableCompletableObserver, params: P) {
        addDisposable(getInvoke(params).subscribeWith(singleObserver))
    }

    override fun getInvoke(params: P): Completable {
        return build(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun dispose() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }
}