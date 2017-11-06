package com.loh.skint.domain.usecase

import io.reactivex.disposables.Disposable

interface UseCase<T, P> {

    fun build(params: P): T

    fun getInvoke(params: P): T

    fun addDisposable(disposable: Disposable)

    fun dispose()
}