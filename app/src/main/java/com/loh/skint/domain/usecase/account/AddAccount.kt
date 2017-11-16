package com.loh.skint.domain.usecase.account

import com.loh.skint.domain.mapper.AccountMapper
import com.loh.skint.domain.model.Account
import com.loh.skint.domain.repository.AccountRepository
import com.loh.skint.domain.usecase.SingleUseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AddAccount @Inject constructor(compositeDisposable: CompositeDisposable,
                                     private val accountMapper: AccountMapper,
                                     private val accountRepository: AccountRepository)
    : SingleUseCase<Account, Account>(compositeDisposable) {

    override fun build(params: Account): Single<Account> {
        val accountEntity = accountMapper.mapDomainToEntity(params)
        return accountRepository.add(accountEntity)
    }
}