package com.los3molineros.logisticstycoon.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.los3molineros.logisticstycoon.model.alreadyExistsUser
import com.los3molineros.logisticstycoon.model.data.Quotes
import com.los3molineros.logisticstycoon.model.returnRandomQuote
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _quote = MutableLiveData<Quotes?>()
    val quote: LiveData<Quotes?> get() = _quote

    private val _userExists = MutableLiveData<Boolean>()
    val userExists : LiveData<Boolean> get() = _userExists

    init {
        viewModelScope.launch {
            returnRandomQuote().collect { _quote.value = it }
            alreadyExistsUser().collect { _userExists.value = it }
        }
    }

}