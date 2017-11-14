package com.loh.skint.domain.repository

import com.loh.skint.data.entity.Account
import java.util.*

interface AccountRepository : Repository<Account, com.loh.skint.domain.model.Account> {
    fun getBlocking(uuid: UUID) : Account
}