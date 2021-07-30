package com.los3molineros.logisticstycoon.model

import com.google.firebase.auth.FirebaseAuth

fun alreadyExistsUser(): Boolean {
    val currentUser = FirebaseAuth.getInstance().currentUser

    return currentUser != null
}