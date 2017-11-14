package com.loh.skint.domain.repository

import com.loh.skint.domain.model.Record
import com.loh.skint.util.DateRange
import io.reactivex.Single
import org.threeten.bp.LocalDate
import java.util.*

interface RecordRepository : Repository<com.loh.skint.data.entity.Record, Record> {
    fun getRange(accountUUID: UUID, date: LocalDate, dateRange: DateRange): Single<MutableList<Record>>
    fun getAllForAccount(accountUUID: UUID): Single<MutableList<Record>>
    fun getRecentRecords(accountUUID: UUID, date: LocalDate): Single<MutableList<Record>>
}