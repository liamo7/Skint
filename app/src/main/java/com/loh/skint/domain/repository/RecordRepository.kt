package com.loh.skint.domain.repository

import com.loh.skint.domain.model.Record
import io.reactivex.Single
import org.threeten.bp.LocalDate

interface RecordRepository : Repository<com.loh.skint.data.entity.Record, com.loh.skint.domain.model.Record> {

    fun getRecentRecords(accountId: Int, date: LocalDate): Single<List<Record>>
}