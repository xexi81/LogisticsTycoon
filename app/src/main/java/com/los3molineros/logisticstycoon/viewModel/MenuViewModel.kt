package com.los3molineros.logisticstycoon.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.los3molineros.logisticstycoon.model.*
import com.los3molineros.logisticstycoon.model.data.Parameters
import com.los3molineros.logisticstycoon.model.data.Users
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MenuViewModel : ViewModel() {
    val parameters = MutableLiveData<Parameters?>()
    val user = MutableLiveData<Users?>()
    val userMenuImage = MutableLiveData<Uri?>()
    val trucksMenuImage = MutableLiveData<Uri?>()
    val routesMenuImage = MutableLiveData<Uri?>()
    val headquarterMenuImage = MutableLiveData<Uri?>()
    val partnershipMenuImage = MutableLiveData<Uri?>()
    val storeMenuImage = MutableLiveData<Uri?>()

    private val _userExists = MutableLiveData<Boolean>()
    val userExists : LiveData<Boolean> get() = _userExists

    init {
        viewModelScope.launch {
            parameters.postValue(selectFirebaseParams())
            alreadyExistsUser().collect { _userExists.value = it }
            selectFirebaseUserFlow().collect {
                user.postValue(it)
            }
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