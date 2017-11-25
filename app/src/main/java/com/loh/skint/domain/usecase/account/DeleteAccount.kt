package com.loh.skint.domain.usecase.account

import com.loh.skint.domain.repository.AccountRepository
import com.loh.skint.domain.usecase.CompletableUseCase
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

class DeleteAccount @Inject constructor(compositeDisposable: CompositeDisposable,
                                        private val repository: AccountRepository)
    : CompletableUseCase<DeleteAccount.Params>(compositeDisposable) {

    override fun build(params: Params): Completable {
        return repository.delete(params.accountUUID)
    }

    data class Params(val accountUUID: UUID)
}