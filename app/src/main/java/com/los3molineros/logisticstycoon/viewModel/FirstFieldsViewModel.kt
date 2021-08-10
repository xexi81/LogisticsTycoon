package com.los3molineros.logisticstycoon.viewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.los3molineros.logisticstycoon.model.returnUriFromStorageCloud
import com.los3molineros.logisticstycoon.model.updateNicknameAndBaseUser
import kotlinx.coroutines.launch

class FirstFieldsViewModel: ViewModel() {
    val nicknameImage = MutableLiveData<Uri?>()
    val baseImage = MutableLiveData<Uri?>()

    fun returnNicknameImage() {
        viewModelScope.launch {
            nicknameImage.postValue(returnUriFromStorageCloud("gs://logistics-tycoon.appspot.com/avatar_1.png"))
        }
    }

    fun returnBaseImage() {
        viewModelScope.launch {
            baseImage.postValue(returnUriFromStorageCloud("gs://logistics-tycoon.appspot.com/headquarter_menu.png"))
        }
    }

    fun assignNicknameAndBase(nickname: String, base: String) {
        viewModelScope.launch {
            updateNicknameAndBaseUser(nickname, base)
        }
    }

}