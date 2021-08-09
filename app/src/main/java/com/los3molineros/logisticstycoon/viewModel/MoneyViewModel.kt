package com.los3molineros.logisticstycoon.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.los3molineros.logisticstycoon.model.selectFirebaseUser
import kotlinx.coroutines.launch

class MoneyViewModel: ViewModel() {
    val money = MutableLiveData<Int>()
    val gems = MutableLiveData<Int>()

    init {
        viewModelScope.launch {
            val user = selectFirebaseUser()

            money.postValue(user?.money ?: 0)
            gems.postValue(user?.gems ?: 0)
        }
    }
}