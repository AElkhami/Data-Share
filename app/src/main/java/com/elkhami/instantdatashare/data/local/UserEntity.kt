package com.elkhami.instantdatashare.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by A.Elkhami on 30/10/2023.
 */
@Entity(tableName = "user_entity")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int=0,
    val brithDate: String
)
