package com.los3molineros.logisticstycoon.model

import com.google.firebase.firestore.FirebaseFirestore
import com.los3molineros.logisticstycoon.model.data.Parameters
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
suspend fun selectFirebaseParams(): Flow<Parameters?> = callbackFlow {
    val resultData = FirebaseFirestore.getInstance()
        .collection("parameters")
        .document("parameters")

    val subscription = resultData.addSnapshotListener { snapshot, _ ->
        if (snapshot!!.exists()) {
            offer(snapshot.toObject(Parameters::class.java))
        }
    }

    awaitClose { subscription.remove() }
}


