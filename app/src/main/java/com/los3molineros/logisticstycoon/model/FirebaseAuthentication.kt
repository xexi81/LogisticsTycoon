package com.los3molineros.logisticstycoon.model

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.los3molineros.logisticstycoon.R
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.*


fun alreadyExistsUser() = flow {
    val currentUser = FirebaseAuth.getInstance().currentUser

    emit(currentUser != null)
}


suspend fun registerUser(email: String, password: String) {
    val currentUser = FirebaseAuth.getInstance()

    currentUser.createUserWithEmailAndPassword(email, password).await()
}

suspend fun returnFirebaseUser(): FirebaseUser? {
    val currentUser = FirebaseAuth.getInstance().currentUser

    return currentUser
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


// Sign Out
fun signOutFirebase(context: Context) {
    val mAuth = FirebaseAuth.getInstance()

    val googleSignInOptions = GoogleSignInOptions.Builder()
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    val mGoogleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)

    mAuth.signOut()
    mGoogleSignInClient.signOut()
}