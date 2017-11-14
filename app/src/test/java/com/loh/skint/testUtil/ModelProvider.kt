package com.loh.skint.testUtil

import com.loh.skint.data.entity.TransferType
import com.loh.skint.domain.model.AVAILABLE_CURRENCIES
import org.threeten.bp.LocalDate
import java.math.BigDecimal
import java.util.*

class ModelProvider {

    companion object {

        fun randomAccount(): Account {
            return Account(
                    randUUID(),
                    randInt(100),
                    "Account ${randInt(100)}",
                    "40.00",
                    AVAILABLE_CURRENCIES[0],
                    LocalDate.of(2017, 4, 1),
                    -1,
                    null)
        }

        fun randomRecord(): Record {
            return Record(
                    randUUID(),
                    randInt(100),
                    randTransferType(),
                    BigDecimal("10.00").multiply(BigDecimal(randInt(50))),
                    LocalDate.of(2017, 5, 5),
                    randUUID())
        }

        fun randUUID() = UUID.randomUUID()
        fun randInt(bound: Int) = Random().nextInt(bound)
        fun randTransferType(): TransferType {
            return if (randInt(2).rem(2) == 0) {
                TransferType.INCOME
            } else {
                TransferType.EXPENSE
            }
        }
    }
}