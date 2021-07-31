package com.los3molineros.logisticstycoon.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.los3molineros.logisticstycoon.model.*
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    val userExists = MutableLiveData<Boolean>()
    val emailSended = MutableLiveData<Boolean>()
    val signedWithGoogle = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            userExists.postValue(alreadyExistsUser())
        }
    }

    fun firebaseRegisterUser(user: String, password: String) {
        viewModelScope.launch {
            registerUser(user,password)
            userExists.postValue(alreadyExistsUser())
        }
    }

    fun firebaseLoginUser(user: String, password: String) {
        viewModelScope.launch {
            loginUser(user,password)
            userExists.postValue(alreadyExistsUser())
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




