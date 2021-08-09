package com.los3molineros.logisticstycoon.model

import com.google.firebase.firestore.FirebaseFirestore
import com.los3molineros.logisticstycoon.model.data.Users
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