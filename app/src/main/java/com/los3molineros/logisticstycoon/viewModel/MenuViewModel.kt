package com.los3molineros.logisticstycoon.viewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.los3molineros.logisticstycoon.model.*
import com.los3molineros.logisticstycoon.model.data.Parameters
import com.los3molineros.logisticstycoon.model.data.Users
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {
    val parameters = MutableLiveData<Parameters?>()
    val userExists = MutableLiveData<Boolean>()
    val user = MutableLiveData<Users?>()
    val userMenuImage = MutableLiveData<Uri?>()
    val trucksMenuImage = MutableLiveData<Uri?>()
    val routesMenuImage = MutableLiveData<Uri?>()
    val headquarterMenuImage = MutableLiveData<Uri?>()
    val partnershipMenuImage = MutableLiveData<Uri?>()
    val storeMenuImage = MutableLiveData<Uri?>()

    init {
        viewModelScope.launch {
            parameters.postValue(selectFirebaseParams())
            userExists.postValue(alreadyExistsUser())
            user.postValue(selectFirebaseUser())
        }
    }

    fun insertOrUpdateUser() {
        viewModelScope.launch {
            selectFirebaseUser()?.let {
                updateFirebaseUser()
            } ?: insertFirebaseUser()
        }
    }

    fun returnUserMenuImage(url: String) {
        viewModelScope.launch {
            userMenuImage.postValue(returnUriFromStorageCloud(url))
        }
    }

    fun returnTrucksMenuImage(url: String) {
        viewModelScope.launch {
            trucksMenuImage.postValue(returnUriFromStorageCloud(url))
        }
    }

    fun returnRoutesMenuImage(url: String) {
        viewModelScope.launch {
            routesMenuImage.postValue(returnUriFromStorageCloud(url))
        }
    }

    fun returnHeadquarterMenuImage(url: String) {
        viewModelScope.launch {
            headquarterMenuImage.postValue(returnUriFromStorageCloud(url))
        }
    }

    fun returnPartnershipMenuImage(url: String) {
        viewModelScope.launch {
            partnershipMenuImage.postValue(returnUriFromStorageCloud(url))
        }
    }

    fun returnStoreMenuImage(url: String) {
        viewModelScope.launch {
            storeMenuImage.postValue(returnUriFromStorageCloud(url))
        }
    }






}