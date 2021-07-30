package com.los3molineros.logisticstycoon.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.los3molineros.logisticstycoon.model.data.Parametros
import com.los3molineros.logisticstycoon.model.data.Quotes
import com.los3molineros.logisticstycoon.model.selectFirebaseParams
import com.los3molineros.logisticstycoon.model.selectRandomQuote
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val version = MutableLiveData<Parametros?>()
    val quote = MutableLiveData<Quotes?>()

    init {
        viewModelScope.launch {
            version.postValue(selectFirebaseParams())
            quote.postValue(selectRandomQuote())
        }
    }
}