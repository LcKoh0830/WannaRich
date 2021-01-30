package com.example.wannarich.injection

import android.content.Context
import androidx.room.Room
import com.example.wannarich.db.AppDatabase
import com.example.wannarich.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideIncomeDao(db: AppDatabase) = db.incomeDao()

    @Singleton
    @Provides
    fun provideExpenseDao(db: AppDatabase) = db.expenseDao()
}