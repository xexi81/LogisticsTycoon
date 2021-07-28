package com.los3molineros.logisticstycoon.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.los3molineros.logisticstycoon.model.data.Parametros
import com.los3molineros.logisticstycoon.model.data.Quotes
import com.los3molineros.logisticstycoon.model.searchSharedPreferencesVerion
import com.los3molineros.logisticstycoon.model.selectFirebaseParams
import com.los3molineros.logisticstycoon.model.selectRandomQuote
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val version = MutableLiveData<Parametros?>()
    val quote = MutableLiveData<Quotes?>()
    val isActualVersion = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            version.postValue(selectFirebaseParams())
            quote.postValue(selectRandomQuote())
        }
    }


    fun searchSharedVersion(context: Context, actualVersion: Int) {
        isActualVersion.postValue(searchSharedPreferencesVerion(context, actualVersion))
    }
}