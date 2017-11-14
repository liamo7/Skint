package com.loh.skint.domain.usecase.account

import com.loh.skint.domain.model.Account
import com.loh.skint.domain.repository.AccountRepository
import com.loh.skint.domain.usecase.SingleUseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class GetAccounts @Inject constructor(compositeDisposable: CompositeDisposable,
                                      private val repository: AccountRepository)
    : SingleUseCase<MutableList<Account>, Unit?>(compositeDisposable) {

    override fun build(params: Unit?): Single<MutableList<Account>> {
        return repository.getAll()
    }
}