package com.los3molineros.logisticstycoon.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.los3molineros.logisticstycoon.model.data.Parameters
import com.los3molineros.logisticstycoon.model.data.Users
import com.los3molineros.logisticstycoon.model.selectFirebaseParams
import com.los3molineros.logisticstycoon.model.selectFirebaseUserFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class UserMenuViewModel : ViewModel() {
    private val _user = MutableLiveData<Users?>()
    val user: LiveData<Users?> get() = _user

    private val _parameters = MutableLiveData<Parameters>()
    val parameters: LiveData<Parameters> get() = _parameters

    init {
        viewModelScope.launch {
            selectFirebaseUserFlow().collect {
                _user.value = it
            }
        }

        viewModelScope.launch {
            selectFirebaseParams().collect {
                _parameters.value = it }
        }
    }

}