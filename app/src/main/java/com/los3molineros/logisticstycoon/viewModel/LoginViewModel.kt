package com.los3molineros.logisticstycoon.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.los3molineros.logisticstycoon.model.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val _userExists = MutableLiveData<Boolean>()
    val userExists : LiveData<Boolean> get() = _userExists

    val emailSended = MutableLiveData<Boolean>()
    val signedWithGoogle = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            alreadyExistsUser().collect { _userExists.value = it }
        }
    }

    fun firebaseRegisterUser(user: String, password: String) {
        viewModelScope.launch {
            registerUser(user,password)
            alreadyExistsUser().collect { _userExists.value = it }
        }
    }

    fun firebaseLoginUser(user: String, password: String) {
        viewModelScope.launch {
            loginUser(user,password)
            alreadyExistsUser().collect { _userExists.value = it }
        }
    }

    fun rememberFirebasePassword(email: String) {
        viewModelScope.launch {
            emailSended.postValue(resetUser(email)!!)
        }
    }

    fun signInWithGoogle(account: GoogleSignInAccount) {
        viewModelScope.launch {
            signedWithGoogle.postValue(firebaseAuthWithGoogle(account)!!)
        }
    }

}




