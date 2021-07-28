package com.los3molineros.logisticstycoon.model

import com.google.firebase.firestore.FirebaseFirestore
import com.los3molineros.logisticstycoon.model.data.Quotes
import kotlinx.coroutines.tasks.await

suspend fun selectRandomQuote(): Quotes? {
    val db = FirebaseFirestore.getInstance()
    val listQuotes: MutableList<Quotes> = mutableListOf()

    val resultData = db.collection("quotes")
        .get()
        .await()

    if (!resultData.isEmpty) {
        for (document in resultData) {
            listQuotes.add(document.toObject(Quotes::class.java))
        }

        val index = (0 until listQuotes.size).random()

        return listQuotes[index]

    } else return null

}