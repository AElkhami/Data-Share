package com.elkhami.instantdatashare.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elkhami.instantdatashare.data.local.UserEntity
import com.elkhami.instantdatashare.data.repository.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by A.Elkhami on 30/10/2023.
 */
class MainViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    fun insertUser(){
        viewModelScope.launch {
            repository.insertUser(UserEntity(1, "15/08/1993"))
        }
    }
}