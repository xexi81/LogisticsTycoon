package com.los3molineros.logisticstycoon.model

import com.google.firebase.firestore.FirebaseFirestore
import com.los3molineros.logisticstycoon.model.data.Parametros
import kotlinx.coroutines.tasks.await

suspend fun selectFirebaseParams(): Parametros? {
    val db = FirebaseFirestore.getInstance()

    val resultData = db.collection("parametros")
        .document("parametros")
        .get()
        .await()

    if (resultData.exists()) {
        return resultData.toObject(Parametros::class.java)
    } else return null
}


