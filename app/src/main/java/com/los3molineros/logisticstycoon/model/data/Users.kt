package com.los3molineros.logisticstycoon.model.data

import java.util.*

data class Users (
    val id: String = "",
    val nickname: String? = "",
    val lastConnectedDate: Date? = null,
    val avatar: Int? = null,
    val base: String? = null,
    val experience: Int = 0,
    val level: Int = 1,
    val money: Int = 0,
    val gems: Int = 0,
    val bread: Int = 0,
    val gasoline: Int = 0,
    val tools: Int = 0,
    val acceptedContracts: Int = 0,
    val doneContracts: Int = 0,
    val medals: Int = 0,
    val partnership: String? = null,
    val administrator: Boolean = false,
    val notifications: Boolean = false,
    val sound: Boolean = false,
    val music: Boolean = false
)