package com.los3molineros.logisticstycoon.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.los3molineros.logisticstycoon.model.data.Users
import com.los3molineros.logisticstycoon.model.selectFirebaseUserFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class UserMenuViewModel: ViewModel() {
    val user = MutableLiveData<Users?>()

    init {
        viewModelScope.launch {
            selectFirebaseUserFlow().collect {
                user.postValue(it)
            }
        }
    }



}