package com.elkhami.instantdatashare.di

import android.content.Context
import androidx.room.Room
import com.elkhami.instantdatashare.data.local.UserDatabase
import com.elkhami.instantdatashare.data.repository.UserRepository
import com.elkhami.instantdatashare.domain.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by A.Elkhami on 30/10/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): UserDatabase {
        return Room.databaseBuilder(
            appContext,
            UserDatabase::class.java,
            "MyDataBase"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(userDatabase: UserDatabase): UserRepository =
        UserRepositoryImpl(userDatabase)
}