package com.los3molineros.logisticstycoon.model

import com.google.firebase.firestore.FirebaseFirestore
import com.los3molineros.logisticstycoon.model.data.Users
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.*

suspend fun selectFirebaseUser(): Users? {
    val db = FirebaseFirestore.getInstance()
    val uuid = returnFirebaseUser()?.uid

    val resultData = uuid?.let {
        db.collection("user")
            .document(it)
            .get()
            .await()
    } ?: return null

    return resultData.toObject(Users::class.java)
}

@ExperimentalCoroutinesApi
suspend fun selectFirebaseUserFlow(): Flow<Users?> = callbackFlow {

    val uuid = returnFirebaseUser()?.uid

    uuid?.let {
        val document = FirebaseFirestore
            .getInstance()
            .collection("user")
            .document(it)

        val subscription = document.addSnapshotListener { documentSnapshot, firebaseFirestoreException ->

            if (documentSnapshot !=null && documentSnapshot.exists()) {
                offer(documentSnapshot.toObject(Users::class.java))
            }
        }
        awaitClose { subscription.remove() }
    }
}




suspend fun insertFirebaseUser() {
    val db = FirebaseFirestore.getInstance()
    val uuid = returnFirebaseUser()?.uid
    val date = Calendar.getInstance().time

    val parameter = selectFirebaseParams()

    uuid?.let {
        val newUser = Users(id = it, nickname = null, lastConnectedDate = date, money = parameter?.initialMoney ?: 50000, gems = parameter?.initialGems ?: 0)
        val resultData = db.collection("user").document(it).set(newUser).await()
    }
}


suspend fun updateFirebaseUser() {
    val db = FirebaseFirestore.getInstance()
    val uuid = returnFirebaseUser()?.uid
    val date = Calendar.getInstance().time

    uuid?.let {
        val resultData = db.collection("user").document(it).update("lastConnectedDate", date)
    }
}


suspend fun updateNicknameAndBaseUser(nickname: String, baseLocation: String) {
    val db = FirebaseFirestore.getInstance()
    val uuid = returnFirebaseUser()?.uid

    uuid?.let {
        val resultData = db.collection("user").document(it).update("nickname", nickname, "base", baseLocation)
    }
}