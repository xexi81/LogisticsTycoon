package com.los3molineros.logisticstycoon.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.los3molineros.logisticstycoon.model.*
import com.los3molineros.logisticstycoon.model.data.Parameters
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {
    val parameters = MutableLiveData<Parameters?>()
    val userExists = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            parameters.postValue(selectFirebaseParams())
            userExists.postValue(alreadyExistsUser())
        }
    }

    fun insertOrUpdateUser() {
        viewModelScope.launch {
            selectFirebaseUser()?.let {
                updateFirebaseUser()
            } ?: insertFirebaseUser()
        }
    }
}