package com.los3molineros.logisticstycoon.model

import com.google.firebase.firestore.FirebaseFirestore
import com.los3molineros.logisticstycoon.model.data.Parameters
import kotlinx.coroutines.tasks.await

suspend fun selectFirebaseParams(): Parameters? {
    val db = FirebaseFirestore.getInstance()

    val resultData = db.collection("parameters")
        .document("parameters")
        .get()
        .await()

    if (resultData.exists()) {
        return resultData.toObject(Parameters::class.java)
    } else return null
}


