package com.los3molineros.logisticstycoon.model

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import java.util.*


fun alreadyExistsUser(): Boolean {
    val currentUser = FirebaseAuth.getInstance().currentUser

    return currentUser != null
}


suspend fun registerUser(email: String, password: String) {
    val currentUser = FirebaseAuth.getInstance()

    currentUser.createUserWithEmailAndPassword(email, password).await()
}

// Login an user on firebase
suspend fun loginUser(email: String, password: String) {
    val currentUser = FirebaseAuth.getInstance()

    currentUser.signInWithEmailAndPassword(email, password).await()
}


//Reset user by email
suspend fun resetUser(email: String): Boolean {
    val currentUser = FirebaseAuth.getInstance()

    currentUser.setLanguageCode(Locale.getDefault().language) // Language user
    try {
        currentUser.sendPasswordResetEmail(email).await()
        return true
    } catch (e: Exception) {
        return false
    }
}


// Login an user on Google
suspend fun firebaseAuthWithGoogle(account: GoogleSignInAccount): Boolean {
    val currentUser = FirebaseAuth.getInstance()
    val credential = GoogleAuthProvider.getCredential(account.idToken, null)

    try {
        currentUser.signInWithCredential(credential).await()
        return true
    } catch (e: Exception) {
        return false
    }

}