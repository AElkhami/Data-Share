package com.elkhami.instantdatashare.domain.repository

import com.elkhami.instantdatashare.data.local.UserDatabase
import com.elkhami.instantdatashare.data.local.UserEntity
import com.elkhami.instantdatashare.data.repository.UserRepository
import javax.inject.Inject

/**
 * Created by A.Elkhami on 30/10/2023.
 */
class UserRepositoryImpl @Inject constructor(private val userDb: UserDatabase)
    : UserRepository {
    override suspend fun insertUser(user: UserEntity) {
        userDb.userDao().insertUser(user)
    }

    override suspend fun getUsers(): List<UserEntity> {
        return userDb.userDao().getAllUsers()
    }
}