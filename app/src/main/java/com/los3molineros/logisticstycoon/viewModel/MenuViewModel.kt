package com.los3molineros.logisticstycoon.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.los3molineros.logisticstycoon.model.alreadyExistsUser
import com.los3molineros.logisticstycoon.model.data.Parameters
import com.los3molineros.logisticstycoon.model.selectFirebaseParams
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
}