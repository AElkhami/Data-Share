package com.elkhami.instantdatashare.data.repository

import com.elkhami.instantdatashare.data.local.UserEntity

/**
 * Created by A.Elkhami on 30/10/2023.
 */
interface UserRepository {
    suspend fun insertUser(user: UserEntity)
    suspend fun getUsers(): List<UserEntity>
}