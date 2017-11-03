package com.loh.skint.domain.model

import java.math.BigDecimal
import java.util.*

data class Account(val uuid: UUID, val name: String, val balance: BigDecimal)