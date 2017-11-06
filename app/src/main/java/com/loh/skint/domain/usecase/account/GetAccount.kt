package com.loh.skint.domain.usecase.account

import com.loh.skint.domain.mapper.AccountMapper
import com.loh.skint.domain.repository.AccountRepository
import com.loh.skint.domain.usecase.SingleUseCase
import com.loh.skint.ui.model.Account
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class GetAccount @Inject constructor(compositeDisposable: CompositeDisposable,
                                     private val repository: AccountRepository,
                                     private val mapper: AccountMapper)
    : SingleUseCase<Account, Int>(compositeDisposable) {

    override fun build(params: Int): Single<Account> {
        return repository.get(params)
                .map { mapper.mapDomainToUi(it) }
    }

}