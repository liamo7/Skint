package com.loh.skint.domain.usecase.record

import com.loh.skint.data.entity.TransferType
import com.loh.skint.domain.mapper.RecordMapper
import com.loh.skint.domain.model.Record
import com.loh.skint.domain.repository.AccountRepository
import com.loh.skint.domain.usecase.CompletableUseCase
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

class AddRecord @Inject constructor(compositeDisposable: CompositeDisposable,
                                    private val accountRepository: AccountRepository,
                                    private val recordMapper: RecordMapper)
    : CompletableUseCase<AddRecord.Params>(compositeDisposable) {

    override fun build(params: Params): Completable {
        val accountEntity = accountRepository.getBlocking(params.accountUUID)
        val recordEntity = recordMapper.mapDomainToEntity(params.record)

        recordEntity.accountUUID = accountEntity.uuid
        recordEntity.account = accountEntity
        recordEntity.currency = accountEntity.currency

        accountEntity.records.add(recordEntity)

        val balance = when (recordEntity.transferType) {
            TransferType.INCOME -> accountEntity.balance.add(recordEntity.amount)
            TransferType.EXPENSE -> accountEntity.balance.subtract(recordEntity.amount)
        }

        accountEntity.balance = balance

        return accountRepository.update(accountEntity).toCompletable()
    }

    data class Params(val accountUUID: UUID, val record: Record)
}