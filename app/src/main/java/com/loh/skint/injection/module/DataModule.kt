package com.loh.skint.injection.module

import android.content.Context
import com.loh.skint.BuildConfig
import com.loh.skint.data.entity.Models
import com.loh.skint.injection.qualifier.App
import dagger.Module
import dagger.Provides
import io.requery.Persistable
import io.requery.android.sqlite.DatabaseSource
import io.requery.reactivex.KotlinReactiveEntityStore
import io.requery.sql.KotlinEntityDataStore
import io.requery.sql.TableCreationMode
import javax.inject.Singleton

@Module
class DataModule {

    @Provides @Singleton
    fun providesEntityDataStore(@App context: Context): KotlinReactiveEntityStore<Persistable> {
        val source = DatabaseSource(context, Models.DEFAULT, "skint.db", 1)
        if (BuildConfig.DEBUG) source.setTableCreationMode(TableCreationMode.DROP_CREATE)
        return KotlinReactiveEntityStore(KotlinEntityDataStore(source.configuration))
    }

    @Provides @Singleton
    fun bindsAccountRepository(repository: com.loh.skint.data.repository.AccountRepository):
            com.loh.skint.domain.repository.AccountRepository = repository

    @Provides @Singleton
    fun bindsRecordRepository(repository: com.loh.skint.data.repository.RecordRepository):
            com.loh.skint.domain.repository.RecordRepository = repository
}