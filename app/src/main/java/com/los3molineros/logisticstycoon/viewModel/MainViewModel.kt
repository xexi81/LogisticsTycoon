package com.los3molineros.logisticstycoon.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.los3molineros.logisticstycoon.model.alreadyExistsUser
import com.los3molineros.logisticstycoon.model.data.Quotes
import com.los3molineros.logisticstycoon.model.selectRandomQuote
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val quote = MutableLiveData<Quotes?>()
    val userExists = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            quote.postValue(selectRandomQuote())
            userExists.postValue(alreadyExistsUser())
        }
    }

}