package com.los3molineros.logisticstycoon.model

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

suspend fun returnUriFromStorageCloud(url: String): Uri? {
    val storage = FirebaseStorage.getInstance().getReferenceFromUrl(url)
    return storage.downloadUrl.await()
}