package com.elkhami.instantdatashare.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by A.Elkhami on 30/10/2023.
 */
@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}