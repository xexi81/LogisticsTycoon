package com.los3molineros.logisticstycoon.viewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.los3molineros.logisticstycoon.model.alreadyExistsUser
import com.los3molineros.logisticstycoon.model.data.Parameters
import com.los3molineros.logisticstycoon.model.data.Quotes
import com.los3molineros.logisticstycoon.model.returnUriFromStorageCloud
import com.los3molineros.logisticstycoon.model.selectFirebaseParams
import com.los3molineros.logisticstycoon.model.selectRandomQuote
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val quote = MutableLiveData<Quotes?>()
    val userExists = MutableLiveData<Boolean>()
    val tittleImage = MutableLiveData<Uri?>()

    init {
        viewModelScope.launch {
            quote.postValue(selectRandomQuote())
            userExists.postValue(alreadyExistsUser())
        }
    }

    fun returnTittle(url: String) {
        viewModelScope.launch {
            tittleImage.postValue(returnUriFromStorageCloud(url))
        }
    }

}