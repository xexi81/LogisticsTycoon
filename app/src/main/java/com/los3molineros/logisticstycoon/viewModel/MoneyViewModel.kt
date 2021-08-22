package com.los3molineros.logisticstycoon.viewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.los3molineros.logisticstycoon.model.returnUriFromStorageCloud
import com.los3molineros.logisticstycoon.model.selectFirebaseUserFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MoneyViewModel: ViewModel() {
    val money = MutableLiveData<Int>()
    val moneyImage = MutableLiveData<Uri?>()
    val gems = MutableLiveData<Int>()
    val gemsImage = MutableLiveData<Uri?>()


    init {
        viewModelScope.launch {
            selectFirebaseUserFlow().collect {
                money.postValue(it?.money ?: 0)
                gems.postValue(it?.gems ?: 0)
            }
        }
    }

    fun returnMoneyImage(url: String) {
        viewModelScope.launch {
            moneyImage.postValue(returnUriFromStorageCloud(url))
        }
    }

    fun returnGemsImage(url: String) {
        viewModelScope.launch {
            gemsImage.postValue(returnUriFromStorageCloud(url))
        }
    }
}