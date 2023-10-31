package com.elkhami.instantdatashare.data.local

import android.database.Cursor
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by A.Elkhami on 30/10/2023.
 */
interface UserDao {
    @Insert
    fun insertUser(vararg users: UserEntity)

    @Query("SELECT * FROM user_entity")
    fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM user_entity")
    fun fetchCursor(): Cursor
}