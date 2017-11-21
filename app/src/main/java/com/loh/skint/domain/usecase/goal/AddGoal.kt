package com.loh.skint.domain.usecase.goal

import com.loh.skint.domain.mapper.GoalMapper
import com.loh.skint.domain.model.Goal
import com.loh.skint.domain.repository.AccountRepository
import com.loh.skint.domain.usecase.CompletableUseCase
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

class AddGoal @Inject constructor(compositeDisposable: CompositeDisposable,
                                  private val accountRepository: AccountRepository,
                                  private val goalMapper: GoalMapper) :
        CompletableUseCase<AddGoal.Params>(compositeDisposable) {


    override fun build(params: AddGoal.Params): Completable {
        val accountEntity = accountRepository.getBlocking(params.accountUUID)
        val goalEntity = goalMapper.mapDomainToEntity(params.goal)

        goalEntity.accountUUID = accountEntity.uuid
        goalEntity.account = accountEntity
        goalEntity.currency = accountEntity.currency
        accountEntity.goals.add(goalEntity)

        return accountRepository.update(accountEntity).toCompletable()
    }

    data class Params(val accountUUID: UUID, val goal: Goal)
}