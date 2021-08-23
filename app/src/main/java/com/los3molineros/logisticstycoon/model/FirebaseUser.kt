package com.los3molineros.logisticstycoon.model

import com.google.firebase.firestore.FirebaseFirestore
import com.los3molineros.logisticstycoon.model.data.Users
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
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
        val resultData = FirebaseFirestore
            .getInstance()
            .collection("user")
            .document(it)

        val subscription = resultData.addSnapshotListener { snapshot, _ ->
            if (snapshot!!.exists()) {
                offer(snapshot.toObject(Users::class.java))
            }
        }
        awaitClose { subscription.remove() }
    }
}

@ExperimentalCoroutinesApi
suspend fun insertFirebaseUser() {
    val db = FirebaseFirestore.getInstance()
    val uuid = returnFirebaseUser()?.uid
    val date = Calendar.getInstance().time

    selectFirebaseParams().collect { parameters ->
        val initialMoney = parameters?.initialMoney ?: 50000
        val initialGems = parameters?.initialGems ?: 0

        uuid?.let { user ->
            val newUser = Users(
                id = user,
                nickname = null,
                lastConnectedDate = date,
                money = initialMoney,
                gems = initialGems
            )
            db.collection("user").document(user).set(newUser).await()
        }
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
        val resultData =
            db.collection("user").document(it).update("nickname", nickname, "base", baseLocation)
    }
}


suspend fun updateFirebaseUserNickname(nickname: String, gems: Int) {
    val db = FirebaseFirestore.getInstance()
    val uuid = returnFirebaseUser()?.uid

    uuid?.let {
        val resultData =
            db.collection("user").document(it).update("nickname", nickname, "gems", gems)
    }
}

suspend fun updateAvatarUser(uuid: String, avatarId: Int) {
    val resultData = FirebaseFirestore
        .getInstance()
        .collection("user")
        .document(uuid)
        .update("avatar", avatarId)
        .await()
}

fun buyUserAvatar(uuid: String, avatarID: Int, money: Int, gems: Int) {
    val resultData = FirebaseFirestore
        .getInstance()
        .collection("user")
        .document(uuid)
        .update("avatar", avatarID, "money", money, "gems", gems)
}